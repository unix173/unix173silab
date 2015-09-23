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

import java.util.List;

@Controller
public class KorisnikControllerAdmin {

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(value = "admin/prikaziKorisnike", method = RequestMethod.GET)
    public String prikaziKorisnike(
            Model model,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "obrisan", required = false) Boolean obrisan
    ) {
        if (keyword == null || keyword.isEmpty()) {
            List<Korisnik> korisnici = korisnikService.vratiKorisnike();
            model.addAttribute("users", korisnici);

        } else {
            List<Korisnik> korisnici = korisnikService.pretraziKorisnikeUserName(keyword);
            if (korisnici.isEmpty()) {
                if (obrisan == null) {
                    model.addAttribute("nijeNasao", "Sistem ne može da pronađe korisnike");
                }
            } else {
                model.addAttribute("nasao", "Sistem je pronašao korisnike");
            }
            model.addAttribute("users", korisnici);
        }
        if (obrisan != null) {
            model.addAttribute("obrisan", "Sistem je obrisao korisnika");
        }
        model.addAttribute("user", new Korisnik());
        return "admin/prikaziKorisnike";
    }

    @RequestMapping(value = "admin/obrisiKorisnikaAkcija", method = RequestMethod.POST)
    public String procesObrisiKorisnika(@ModelAttribute("user") Korisnik korisnik, Model model) {
        korisnikService.obrisiKorisnika(korisnik);
        return "redirect:/admin/prikaziKorisnike?keyword=NULL&obrisan=true";
    }
}
