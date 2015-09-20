package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.ItemType;
import com.zuehlke.sistemzaizdavanjevozila.service.ItemTypeService;
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

    private ItemTypeService itemTypeService;

    @Autowired
    public TipVozilaController(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }

    @RequestMapping(value = "admin/prikazTipaVozila", method = RequestMethod.GET)
    public String prikaziTipovaVozila(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            model.addAttribute("itemTypes", itemTypeService.getItemTypes());
        } else {
            model.addAttribute("itemTypes", itemTypeService.getItemTypeByName(keyword));
        }
        model.addAttribute("itemType", new ItemType());
        return "admin/prikazTipaVozila";
    }

    @RequestMapping(value = "admin/izmenaTipaVozila", method = RequestMethod.GET)
    public String izmeniTipVozila(@ModelAttribute("itemType") ItemType itemTypeHelper, Model model) {
        ItemType itemType = itemTypeService.getItemTypeById(itemTypeHelper.getId());
        model.addAttribute("itemType", itemType);
        return "admin/izmenaTipaVozila";
    }

    @RequestMapping(value = "admin/promeniTipVozilaAkcija", method = RequestMethod.POST)
    public String procesIzmeneTipaVozila(@ModelAttribute("itemType") ItemType itemTypeHelper, RedirectAttributes redirectAttributes) {
        ItemType itemType = itemTypeService.getItemTypeById(itemTypeHelper.getId());
        redirectAttributes.addFlashAttribute("itemType", itemType);
        return "redirect:/admin/izmenaTipaVozila";
    }

    @RequestMapping(value = "admin/izmeniTipVozilaAkcija", method = RequestMethod.POST)
    public String akcijaIzmeneTipaVozila(@ModelAttribute("itemType") ItemType itemType, Model model) {
        itemTypeService.setItemType(itemType);
        return "redirect:/admin/prikazTipaVozila";
    }

    @RequestMapping(value = "admin/obrisiTipVozila", method = RequestMethod.POST)
    public String procesBrisanjaTipaVozila(@ModelAttribute("itemType") ItemType itemType, Model model) {
        itemTypeService.deleteItemType(itemType);
        return "redirect:/admin/prikazTipaVozila";
    }

    @RequestMapping(value = "admin/prikaziVozila", method = RequestMethod.GET)
    public String prikaziVozilaPoTipuVozila(@ModelAttribute("itemType") ItemType itemTypeHelper, Model model) {
        model.addAttribute("itemType", itemTypeService.getItemTypeById(itemTypeHelper.getId()));
        return "admin/prikaziVozila";
    }

    @RequestMapping(value = "admin/dodajTipVozila", method = RequestMethod.GET)
    public String prikazDodajTipVozila(Model model) {
        model.addAttribute("itemType", new ItemType());
        return "admin/dodajTipVozila";
    }

    @RequestMapping(value = "admin/dodajTipVozila", method = RequestMethod.POST)
    public String procesDodajTipVozila(@ModelAttribute("itemType") ItemType itemType, Model model) {
        itemTypeService.addItemType(itemType);
        return "redirect:/pocetna";
    }

}
