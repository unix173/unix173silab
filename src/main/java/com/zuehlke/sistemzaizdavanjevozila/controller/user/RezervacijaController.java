package com.zuehlke.sistemzaizdavanjevozila.controller.user;

import com.zuehlke.sistemzaizdavanjevozila.core.ReservationUtil;
import com.zuehlke.sistemzaizdavanjevozila.form.AddReservationEntryForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.model.Rezervacija;
import com.zuehlke.sistemzaizdavanjevozila.model.ReservationEntry;
import com.zuehlke.sistemzaizdavanjevozila.service.TipVozilaService;
import com.zuehlke.sistemzaizdavanjevozila.service.RezervacijaService;
import com.zuehlke.sistemzaizdavanjevozila.service.KorisnikService;
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

    @RequestMapping(value = "user/dodajRezervaciju", method = RequestMethod.GET)
    public String prikazDodajRezervaciju(Model model) {
        if (!model.containsAttribute("addReservationEntryForms")) {
            model.addAttribute("addReservationEntryForms", new ArrayList<AddReservationEntryForm>());
        }
        if (!model.containsAttribute("addReservationEntryForm")) {
            model.addAttribute("addReservationEntryForm", new AddReservationEntryForm());
        }
        model.addAttribute("reservationInProgress", false);
        return "user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/dodajRezervaciju", method = RequestMethod.POST)
    public String procesDodajRezervaciju(@ModelAttribute("addReservationEntryForm") @Valid AddReservationEntryForm addReservationEntryForm,
                                         BindingResult result, Model model,
                                         @ModelAttribute("addReservationEntryForms") List<AddReservationEntryForm> addReservationEntryForms) {
        if (result.hasErrors()) {
            model.addAttribute("reservationInProgress", false);
            model.addAttribute("dateValidationError", "Invalid date interval");
            addReservationEntryForm.setReservationEndDate(null);
            addReservationEntryForm.setReservationStartDate(null);
        } else {
            model.addAttribute("reservationInProgress", true);
            model.addAttribute("itemTypeDTOList", tipVozilaService.getAvailableItemTypes(ReservationUtil.reservedItemTypeIdList(addReservationEntryForms),
                    addReservationEntryForm.getReservationStartDate(), addReservationEntryForm.getReservationEndDate()));
        }
        model.addAttribute("addReservationEntryForm", addReservationEntryForm);
        return "user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/dodajStavkuRezervacijeAkcija", method = RequestMethod.POST)
    public String procesDodajStavkuRezervacije(@ModelAttribute("addReservationEntryForm") @Valid AddReservationEntryForm addReservationEntryForm, BindingResult result, @ModelAttribute("addReservationEntryForms") List<AddReservationEntryForm> addReservationEntryForms,
                                               WebRequest request, Errors errors, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            addReservationEntryForm.setDesiredQuantity(null);
            redirectAttributes.addFlashAttribute("addReservationEntryForm", addReservationEntryForm);
            redirectAttributes.addFlashAttribute("validationFailed", true);
            redirectAttributes.addFlashAttribute("itemTypeDTOList", tipVozilaService.getAvailableItemTypes(ReservationUtil.reservedItemTypeIdList(addReservationEntryForms),
                    addReservationEntryForm.getReservationStartDate(), addReservationEntryForm.getReservationEndDate()));
            return "redirect:/user/dodajRezervaciju";
        } else {
            addReservationEntryForms.add(addReservationEntryForm);
        }
        return "redirect:/user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/dodajRezervacijuAkcija", method = RequestMethod.POST)
    public String dodajRezervacijuAkcija(@ModelAttribute("addReservationEntryForms") List<AddReservationEntryForm> addReservationEntryForms, Principal principal, RedirectAttributes redirectAttributes) {

        rezervacijaService.addReservation(rezervacijaService.createReservation(addReservationEntryForms, korisnikService.getUserByUsername(((User) ((Authentication) principal).getPrincipal()).getUsername())));
        redirectAttributes.addFlashAttribute("successfulReservation", true);
        //do a redirect to user's reservation page
        redirectAttributes.addFlashAttribute("addReservationEntryForms", new ArrayList<AddReservationEntryForm>());
        redirectAttributes.addFlashAttribute("reservationEntry", new ReservationEntry());
        return "redirect:/user/dodajRezervaciju";
    }

    @RequestMapping(value = "user/restartujRezervacijuAkcija", method = RequestMethod.POST)
    public String restartujRezervaciju(@ModelAttribute("addReservationEntryForms") List<AddReservationEntryForm> addReservationEntryForms){
        addReservationEntryForms.clear();
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
        List<ReservationEntry> reservationEntries = new ArrayList<ReservationEntry>(rezervacijaService.getReservationById(rezervacija.getId()).getReservationEntries());
        model.addAttribute("reservationEntries", reservationEntries);
        return "user/prikazStavkiRezervacijeKorisnika";
    }



}