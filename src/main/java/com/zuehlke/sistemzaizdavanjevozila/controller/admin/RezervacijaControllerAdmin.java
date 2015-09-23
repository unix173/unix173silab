package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.model.Rezervacija;
import com.zuehlke.sistemzaizdavanjevozila.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RezervacijaControllerAdmin {

    @Autowired
    private RezervacijaService rezervacijaService;

    @RequestMapping(value = "admin/prikazRezervacija", method = RequestMethod.GET)
    public String prikaziRezervacije(Model model) {
        model.addAttribute("reservations", rezervacijaService.vratiRezervacije());
        return "admin/prikazRezervacija";
    }

    @RequestMapping(value = "admin/prikazRezervacijaPoKorisniku", method = RequestMethod.GET)
    public String priaziRezervacijePoKorisniku(@ModelAttribute("user") Korisnik korisnik, Model model) {
        List<Rezervacija> rezervacijas = rezervacijaService.vratiRezervacijeUserID(korisnik.getId());
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setKorisnik(new Korisnik());
        model.addAttribute("reservation", rezervacija);
        model.addAttribute("reservations", rezervacijas);
        model.addAttribute("user", korisnik);
        return "admin/prikazRezervacijaPoKorisniku";
    }

    @RequestMapping(value = "admin/obrisiRezervacijuAkcija", method = RequestMethod.POST)
    public String obrisiRezervaciju(@ModelAttribute("reservation") Rezervacija rezervacija, RedirectAttributes redirectAttributes) {
        rezervacija = rezervacijaService.ucitajRezervacijuID(rezervacija.getId());
        Long userId = rezervacija.getKorisnik().getId();
        rezervacijaService.obrisiRezervaciju(rezervacija);
        redirectAttributes.addFlashAttribute("reservations", rezervacijaService.vratiRezervacijeUserID(userId));
        redirectAttributes.addFlashAttribute("user", rezervacija.getKorisnik());
        redirectAttributes.addFlashAttribute("obrisana", "Sistem ne može da učita podatke o korisniku");
        return "redirect:/admin/prikazRezervacijaPoKorisniku?id=" + userId;
    }

    @RequestMapping(value = "admin/prikazStavkiRezervacija", method = RequestMethod.GET)
    public String prikaziStavkeRezervacijeAkcija(@ModelAttribute("reservation") Rezervacija rezervacija, Model model) {
        model.addAttribute("reservationEntries", rezervacijaService.ucitajRezervacijuID(rezervacija.getId()).getStavkeRezervacije());
        return "admin/prikazStavkiRezervacija";
    }
}
