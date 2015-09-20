package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.TipVozila;
import com.zuehlke.sistemzaizdavanjevozila.service.TipVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TipVozilaController {

    private TipVozilaService tipVozilaService;

    @Autowired
    public TipVozilaController(TipVozilaService tipVozilaService) {
        this.tipVozilaService = tipVozilaService;
    }

    @RequestMapping(value = "admin/prikazTipaVozila", method = RequestMethod.GET)
    public String prikaziTipovaVozila(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            model.addAttribute("itemTypes", tipVozilaService.getItemTypes());
        } else {
            model.addAttribute("itemTypes", tipVozilaService.getItemTypeByName(keyword));
        }
        model.addAttribute("itemType", new TipVozila());
        return "admin/prikazTipaVozila";
    }

    @RequestMapping(value = "admin/izmenaTipaVozila", method = RequestMethod.GET)
    public String izmeniTipVozila(@ModelAttribute("itemType") TipVozila tipVozilaHelper, Model model) {
        TipVozila tipVozila = tipVozilaService.getItemTypeById(tipVozilaHelper.getId());
        model.addAttribute("itemType", tipVozila);
        return "admin/izmenaTipaVozila";
    }

    @RequestMapping(value = "admin/promeniTipVozilaAkcija", method = RequestMethod.POST)
    public String procesIzmeneTipaVozila(@ModelAttribute("itemType") TipVozila tipVozilaHelper, RedirectAttributes redirectAttributes) {
        TipVozila tipVozila = tipVozilaService.getItemTypeById(tipVozilaHelper.getId());
        redirectAttributes.addFlashAttribute("itemType", tipVozila);
        return "redirect:/admin/izmenaTipaVozila";
    }

    @RequestMapping(value = "admin/izmeniTipVozilaAkcija", method = RequestMethod.POST)
    public String akcijaIzmeneTipaVozila(@ModelAttribute("itemType") TipVozila tipVozila, Model model) {
        tipVozilaService.setItemType(tipVozila);
        return "redirect:/admin/prikazTipaVozila";
    }

    @RequestMapping(value = "admin/obrisiTipVozila", method = RequestMethod.POST)
    public String procesBrisanjaTipaVozila(@ModelAttribute("itemType") TipVozila tipVozila, Model model) {
        tipVozilaService.deleteItemType(tipVozila);
        return "redirect:/admin/prikazTipaVozila";
    }

    @RequestMapping(value = "admin/prikaziVozila", method = RequestMethod.GET)
    public String prikaziVozilaPoTipuVozila(@ModelAttribute("itemType") TipVozila tipVozilaHelper, Model model) {
        model.addAttribute("itemType", tipVozilaService.getItemTypeById(tipVozilaHelper.getId()));
        return "admin/prikaziVozila";
    }

    @RequestMapping(value = "admin/dodajTipVozila", method = RequestMethod.GET)
    public String prikazDodajTipVozila(Model model) {
        model.addAttribute("itemType", new TipVozila());
        return "admin/dodajTipVozila";
    }

    @RequestMapping(value = "admin/dodajTipVozila", method = RequestMethod.POST)
    public String procesDodajTipVozila(@ModelAttribute("itemType") TipVozila tipVozila, Model model) {
        tipVozilaService.addItemType(tipVozila);
        return "redirect:/pocetna";
    }

}
