package com.zuehlke.sistemzaizdavanjevozila.controller.user;

import com.zuehlke.sistemzaizdavanjevozila.core.RezervacijaUtil;
import com.zuehlke.sistemzaizdavanjevozila.form.DodajStavkuRezervacijeForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.model.Rezervacija;
import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;
import com.zuehlke.sistemzaizdavanjevozila.service.KorisnikService;
import com.zuehlke.sistemzaizdavanjevozila.service.RezervacijaService;
import com.zuehlke.sistemzaizdavanjevozila.service.TipVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"addReservationEntryForms"})
public class RezervacijaController {

    @Autowired
    private RezervacijaService rezervacijaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private TipVozilaService tipVozilaService;

    @Autowired
    RezervacijaUtil rezervacijaUtil;

    @RequestMapping(value = "user/dodajRezervaciju", method = RequestMethod.GET)
    public String prikazDodajRezervaciju(Model model) {
        if (!model.containsAttribute("addReservationEntryForms")) {
            model.addAttribute("addReservationEntryForms", new ArrayList<DodajStavkuRezervacijeForm>());
        }
        if (!model.containsAttribute("addReservationEntryForm")) {
            model.addAttribute("addReservationEntryForm", new DodajStavkuRezervacijeForm());
        }
        model.addAttribute("reservationInProgress", false);
        return "user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/dodajRezervaciju", method = RequestMethod.POST)
    public String procesDodajRezervaciju(@ModelAttribute("addReservationEntryForm") @Valid DodajStavkuRezervacijeForm dodajStavkuRezervacijeForm,
                                         BindingResult result, Model model,
                                         @ModelAttribute("addReservationEntryForms") List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms) {
        if (result.hasErrors()) {
            model.addAttribute("reservationInProgress", false);
            model.addAttribute("dateValidationError", "Invalid date interval");
            dodajStavkuRezervacijeForm.setReservationEndDate(null);
            dodajStavkuRezervacijeForm.setReservationStartDate(null);
        } else {
            model.addAttribute("reservationInProgress", true);
            model.addAttribute("itemTypeDTOList", tipVozilaService.getAvailableItemTypes(rezervacijaUtil.reservedItemTypeIdList(dodajStavkuRezervacijeForms),
                    dodajStavkuRezervacijeForm.getReservationStartDate(), dodajStavkuRezervacijeForm.getReservationEndDate()));
        }
        model.addAttribute("addReservationEntryForm", dodajStavkuRezervacijeForm);
        return "user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/dodajStavkuRezervacijeAkcija", method = RequestMethod.POST)
    public String procesDodajStavkuRezervacije(@ModelAttribute("addReservationEntryForm") @Valid DodajStavkuRezervacijeForm dodajStavkuRezervacijeForm, BindingResult result, @ModelAttribute("addReservationEntryForms") List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms,
                                               WebRequest request, Errors errors, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            dodajStavkuRezervacijeForm.setDesiredQuantity(null);
            redirectAttributes.addFlashAttribute("addReservationEntryForm", dodajStavkuRezervacijeForm);
            redirectAttributes.addFlashAttribute("validationFailed", true);
            redirectAttributes.addFlashAttribute("itemTypeDTOList", tipVozilaService.getAvailableItemTypes(rezervacijaUtil.reservedItemTypeIdList(dodajStavkuRezervacijeForms),
                    dodajStavkuRezervacijeForm.getReservationStartDate(), dodajStavkuRezervacijeForm.getReservationEndDate()));
            return "redirect:/user/dodajRezervaciju";
        } else {
            dodajStavkuRezervacijeForms.add(dodajStavkuRezervacijeForm);
        }
        return "redirect:/user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/dodajRezervacijuAkcija", method = RequestMethod.POST)
    public String dodajRezervacijuAkcija(@ModelAttribute("addReservationEntryForms") List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms, Principal principal, RedirectAttributes redirectAttributes) {

        rezervacijaService.addReservation(rezervacijaService.createReservation(dodajStavkuRezervacijeForms, korisnikService.getUserByUsername(((User) ((Authentication) principal).getPrincipal()).getUsername())));
        redirectAttributes.addFlashAttribute("successfulReservation", true);
        //do a redirect to user's reservation page
        redirectAttributes.addFlashAttribute("addReservationEntryForms", new ArrayList<DodajStavkuRezervacijeForm>());
        redirectAttributes.addFlashAttribute("reservationEntry", new StavkaRezervacije());
        return "redirect:/user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/restartujRezervacijuAkcija", method = RequestMethod.POST)
    public String restartujRezervaciju(@ModelAttribute("addReservationEntryForms") List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms){
        dodajStavkuRezervacijeForms.clear();
        return "redirect:/user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/prikazRezervacijaKorisnika", method = RequestMethod.GET)
    public String prikaziRezervacijeKorisnika(Model model, Principal principal) {
        List<Rezervacija> rezervacijas = rezervacijaService.getReservationsByUserId(korisnikService.getUserByUsername(((User) ((Authentication) principal).getPrincipal()).getUsername()).getId());
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setKorisnik(new Korisnik());
        model.addAttribute("reservation", rezervacija);
        model.addAttribute("reservations", rezervacijas);
        return "user/prikazRezervacijaKorisnika";
    }

    @RequestMapping(value = "user/prikazStavkiRezervacijeKorisnika", method = RequestMethod.GET)
    public String prikaziStavkeRezervacijeKorisnikaAkcija(@ModelAttribute("reservation") Rezervacija rezervacija, Model model) {
        List<StavkaRezervacije> reservationEntries = new ArrayList<StavkaRezervacije>(rezervacijaService.getReservationById(rezervacija.getId()).getReservationEntries());
        model.addAttribute("reservationEntries", reservationEntries);
        return "user/prikazStavkiRezervacijeKorisnika";
    }



}