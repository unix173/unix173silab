package com.zuehlke.sistemzaizdavanjevozila.controller.admin;

import com.zuehlke.sistemzaizdavanjevozila.model.User;
import com.zuehlke.sistemzaizdavanjevozila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserControllerAdmin {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin/viewUsers", method = RequestMethod.GET)
    public String allUsersView(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            model.addAttribute("users", userService.getUsers());
        }else {
            model.addAttribute("users", userService.getUsersByUsername(keyword));
        }
        model.addAttribute("user", new User());
        return "admin/viewUsers";
    }

    @RequestMapping(value = "admin/deleteUserAction", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("user") User user, Model model) {
        userService.deleteUser(user);
        return "redirect:/admin/viewUsers";
    }
}
