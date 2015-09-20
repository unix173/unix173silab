package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KorisnikControllerAdmin {

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(value = "admin/prikaziKorisnike", method = RequestMethod.GET)
    public String prikaziKorisnike(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            model.addAttribute("users", korisnikService.getUsers());
        }else {
            model.addAttribute("users", korisnikService.getUsersByUsername(keyword));
        }
        model.addAttribute("user", new Korisnik());
        return "admin/prikaziKorisnike";
    }

    @RequestMapping(value = "admin/obrisiKorisnikaAkcija", method = RequestMethod.POST)
    public String procesObrisiKorisnika(@ModelAttribute("user") Korisnik korisnik, Model model) {
        korisnikService.deleteUser(korisnik);
        return "redirect:/admin/prikaziKorisnike";
    }
}
