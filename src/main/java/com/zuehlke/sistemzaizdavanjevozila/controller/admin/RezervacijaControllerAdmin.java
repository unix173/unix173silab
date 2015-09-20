package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.Reservation;
import com.zuehlke.sistemzaizdavanjevozila.service.ReservationService;
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
    private ReservationService reservationService;

    @RequestMapping(value = "admin/prikazRezervacija", method = RequestMethod.GET)
    public String prikaziRezervacije(Model model) {
        model.addAttribute("reservations", reservationService.getReservations());
        return "admin/prikazRezervacija";
    }

    @RequestMapping(value = "admin/prikazRezervacijaPoKorisniku", method = RequestMethod.GET)
    public String priaziRezervacijePoKorisniku(@ModelAttribute("user") com.zuehlke.sistemzaizdavanjevozila.model.User user, Model model) {
        List<Reservation> reservations = reservationService.getReservationsByUserId(user.getId());
        Reservation reservation = new Reservation();
        reservation.setUser(new com.zuehlke.sistemzaizdavanjevozila.model.User());
        model.addAttribute("reservation", reservation);
        model.addAttribute("reservations", reservations);
        model.addAttribute("user", user);
        return "admin/prikazRezervacijaPoKorisniku";
    }

    @RequestMapping(value = "admin/obrisiRezervacijuAkcija", method = RequestMethod.POST)
    public String obrisiRezervaciju(@ModelAttribute("reservation") Reservation reservation, RedirectAttributes redirectAttributes) {
        reservation = reservationService.getReservationById(reservation.getId());
        Long userId = reservation.getUser().getId();
        reservationService.deleteReservation(reservation);
        redirectAttributes.addFlashAttribute("reservations", reservationService.getReservationsByUserId(userId));
        redirectAttributes.addFlashAttribute("user", reservation.getUser());
        return "redirect:/admin/prikazRezervacijaPoKorisniku?id=" + userId;
    }

    @RequestMapping(value = "admin/prikazStavkiRezervacija", method = RequestMethod.GET)
    public String prikaziStavkeRezervacijeAkcija(@ModelAttribute("reservation") Reservation reservation, Model model) {
        model.addAttribute("reservationEntries", reservationService.getReservationById(reservation.getId()).getReservationEntries());
        return "admin/prikazStavkiRezervacija";
    }
}
