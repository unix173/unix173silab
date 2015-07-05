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
public class ItemTypeController {

    private ItemTypeService itemTypeService;

    @Autowired
    public ItemTypeController(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }

    @RequestMapping(value = "admin/viewItemTypes", method = RequestMethod.GET)
    public String allItemTypesView(Model model,@RequestParam(value = "keyword", required = false) String keyword ) {
        if (keyword == null || keyword.isEmpty()) {
            model.addAttribute("itemTypes", itemTypeService.getItemTypes());
        } else {
            model.addAttribute("itemTypes", itemTypeService.getItemTypeByName(keyword));
        }
        model.addAttribute("itemType", new ItemType());
        return "admin/viewItemTypes";
    }

    @RequestMapping(value = "admin/modifyItemType", method = RequestMethod.GET)
    public String modifyItemTypeView(@ModelAttribute("itemType")ItemType itemTypeHelper, Model model) {
        ItemType itemType = itemTypeService.getItemTypeById(itemTypeHelper.getId());
        model.addAttribute("itemType", itemType);
        return "admin/modifyItemType";
    }

    @RequestMapping(value = "admin/modifyItemTypeAction", method = RequestMethod.POST)
    public String allItemTypesViewModify(@ModelAttribute("itemType")ItemType itemTypeHelper, RedirectAttributes redirectAttributes) {
        ItemType itemType = itemTypeService.getItemTypeById(itemTypeHelper.getId());
        redirectAttributes.addFlashAttribute("itemType", itemType);
        return "redirect:/admin/modifyItemType";
    }

    @RequestMapping(value = "admin/updateItemTypeAction", method = RequestMethod.POST)
    public String allItemTypesProcessModify(@ModelAttribute("itemType") ItemType itemType, Model model) {
        itemTypeService.setItemType(itemType);
        return "redirect:/admin/viewItemTypes";
    }

    @RequestMapping(value = "admin/deleteItemType", method = RequestMethod.POST)
    public String allItemTypesProcessDelete(@ModelAttribute("itemType") ItemType itemType, Model model) {
        itemTypeService.deleteItemType(itemType);
        return "redirect:/admin/viewItemTypes";
    }

    @RequestMapping(value = "admin/viewItems", method = RequestMethod.GET)
    public String addItemsPerItemTypeView(@ModelAttribute("itemType") ItemType itemTypeHelper, Model model) {
        model.addAttribute("itemType", itemTypeService.getItemTypeById(itemTypeHelper.getId()));
        return "admin/viewItems";
    }

    @RequestMapping(value = "admin/addItemType", method = RequestMethod.GET)
    public String addItemTypeForm(Model model) {
        model.addAttribute("itemType", new ItemType());
        return "admin/addItemType";
    }

    @RequestMapping(value = "admin/addItemType", method = RequestMethod.POST)
    public String addItemType(@ModelAttribute("itemType") ItemType itemType, Model model) {
        itemTypeService.addItemType(itemType);
        return "redirect:/home";
    }

}
