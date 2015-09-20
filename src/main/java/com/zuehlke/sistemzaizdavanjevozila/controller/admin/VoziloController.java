package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.Item;
import com.zuehlke.sistemzaizdavanjevozila.service.ItemService;
import com.zuehlke.sistemzaizdavanjevozila.service.TipVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VoziloController {

    private ItemService itemService;
    private TipVozilaService tipVozilaService;

    @Autowired
    public VoziloController(ItemService itemService, TipVozilaService tipVozilaService) {
        this.itemService = itemService;
        this.tipVozilaService = tipVozilaService;
    }

    @RequestMapping(value = "admin/dodajVozilo", method = RequestMethod.GET)
    public String prikaziDodavanjeVozila(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("itemTypes", tipVozilaService.getItemTypes());
        return "admin/dodajVozilo";
    }

    @RequestMapping(value = "admin/dodajVozilo", method = RequestMethod.POST)
    public String procesDodavanjaVozila(@ModelAttribute("item") Item item, Model model) {
        itemService.addItem(item);
        return "redirect:/pocetna";
    }

}
