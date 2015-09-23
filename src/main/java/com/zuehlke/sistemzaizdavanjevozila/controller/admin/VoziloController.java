package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;
import com.zuehlke.sistemzaizdavanjevozila.service.VoziloService;
import com.zuehlke.sistemzaizdavanjevozila.service.TipVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VoziloController {

    private VoziloService voziloService;
    private TipVozilaService tipVozilaService;

    @Autowired
    public VoziloController(VoziloService voziloService, TipVozilaService tipVozilaService) {
        this.voziloService = voziloService;
        this.tipVozilaService = tipVozilaService;
    }

    @RequestMapping(value = "admin/dodajVozilo", method = RequestMethod.GET)
    public String prikaziDodavanjeVozila(
            Model model,
            @RequestParam(value = "dodatoVozilo", required = false) String dodatoVozilo
    ) {
        model.addAttribute("item", new Vozilo());
        model.addAttribute("itemTypes", tipVozilaService.vratiTipoveVozila());
        if (dodatoVozilo != null) {
            model.addAttribute("dodatoVozilo", "Sistem je zapamtio vozilo");
        }
        return "admin/dodajVozilo";
    }

    @RequestMapping(value = "admin/dodajVozilo", method = RequestMethod.POST)
    public String procesDodavanjaVozila(@ModelAttribute("item") Vozilo vozilo, Model model) {
        try {
            voziloService.sacuvajVozilo(vozilo);
        }catch (Exception e){
            return "redirect:/admin/dodajVozilo?nijeDodatoVozilo";
        }
        return "redirect:/admin/dodajVozilo?dodatoVozilo";
    }

}
