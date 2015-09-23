package com.zuehlke.sistemzaizdavanjevozila.controller.user;

import com.zuehlke.sistemzaizdavanjevozila.form.IzmenaLozinkeForm;
import com.zuehlke.sistemzaizdavanjevozila.form.RegistracijaKorisnikaForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.service.MailingService;
import com.zuehlke.sistemzaizdavanjevozila.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MailingService mailingService;

    @RequestMapping(value = "registracija", method = RequestMethod.GET)
    public String prikazRegistracije(Model model) {
        model.addAttribute("userRegistrationForm", new RegistracijaKorisnikaForm());
        return "registracija";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.GET)
    public String potvrdaEmaila(Model model, @RequestParam(value = "confirmationId") String confirmationId){
        if(korisnikService.confirmRegistration(confirmationId)){
            model.addAttribute("msg", "Email je uspešno potvrđen, molimo prijavite se.");
        }
        else{
            model.addAttribute("msg","URL za potvrdu nije ispravan");
        }
        return "prijavljivanje";
    }

    @RequestMapping(value = "registracija", method = RequestMethod.POST)
    public ModelAndView procesRegistracije(@ModelAttribute("userRegistrationForm") @Valid RegistracijaKorisnikaForm registracijaKorisnikaForm, BindingResult result, WebRequest request, Errors errors) {
        if (result.hasErrors()) {
            return new ModelAndView("registracija", "userRegistrationForm", registracijaKorisnikaForm);
        } else if (!korisnikService.checkIfUnique(registracijaKorisnikaForm)) {
            result.addError(new ObjectError("message", "Korisnička šifra ili email su duplirani"));
            return new ModelAndView("registracija", "userRegistrationForm", registracijaKorisnikaForm);
        } else {
            final String confirmationId = UUID.randomUUID().toString().replaceAll("-", "");
            mailingService.sendMail(registracijaKorisnikaForm.getEmail(),"Potvrda registracije", "localhost:8080/confirm?confirmationId=" + confirmationId);
            korisnikService.sacuvajKorisnika(registracijaKorisnikaForm, confirmationId);
        }
        return new ModelAndView("redirect:/prijavljivanje?registered");
    }

    @RequestMapping(value = "user/izmeniLozinku", method = RequestMethod.GET)
    public ModelAndView izmeniProfilKorisnika(Principal principal) {
        Korisnik currentKorisnik = korisnikService.ucitajKorisnikaUsername(((org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal()).getUsername());
        IzmenaLozinkeForm izmenaLozinkeForm = new IzmenaLozinkeForm();
        izmenaLozinkeForm.setId(currentKorisnik.getId());
        izmenaLozinkeForm.setEmail(currentKorisnik.getEmail());
        izmenaLozinkeForm.setName(currentKorisnik.getIme());
        izmenaLozinkeForm.setLastName(currentKorisnik.getPrezime());
        izmenaLozinkeForm.setUsername(currentKorisnik.getUsername());
        return new ModelAndView("user/izmeniLozinku", "changePasswordForm", izmenaLozinkeForm);
    }

    @RequestMapping(value = "user/izmeniLozinku", method = RequestMethod.POST)
    public ModelAndView procesIzmeneProfilaKorisnika(@ModelAttribute("changePasswordForm") @Valid IzmenaLozinkeForm izmenaLozinkeForm, BindingResult result, WebRequest request, Errors errors) {
        if (result.hasErrors()) {
            return new ModelAndView("user/izmeniLozinku", "changePasswordForm", izmenaLozinkeForm);
        } else if (!korisnikService.checkIfPasswordIsCorrect(izmenaLozinkeForm.getId(), izmenaLozinkeForm.getOldPassword())) {
            result.addError(new ObjectError("message", "Sistem ne može da izmeni korisnika!"));
            return new ModelAndView("user/izmeniLozinku", "changePasswordForm", izmenaLozinkeForm);
        } else {
            korisnikService.izmeniKorisnika(izmenaLozinkeForm);
        }
        return new ModelAndView("redirect:/pocetna?izmenjenKorisnik");
    }

}
