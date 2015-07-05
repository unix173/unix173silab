package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.Item;
import com.zuehlke.sistemzaizdavanjevozila.service.ItemService;
import com.zuehlke.sistemzaizdavanjevozila.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ItemController {

    private ItemService itemService;
    private ItemTypeService itemTypeService;

    @Autowired
    public ItemController(ItemService itemService, ItemTypeService itemTypeService) {
        this.itemService = itemService;
        this.itemTypeService = itemTypeService;
    }

    @RequestMapping(value = "admin/addItem", method = RequestMethod.GET)
    public String itemView(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("itemTypes", itemTypeService.getItemTypes());
        return "admin/addItem";
    }

    @RequestMapping(value = "admin/addItem", method = RequestMethod.POST)
    public String itemProcess(@ModelAttribute("item") Item item, Model model) {
        itemService.addItem(item);
        return "redirect:/home";
    }

}
