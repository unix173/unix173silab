package com.zuehlke.sistemzaizdavanjevozila.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GlavniController {

    private final static Logger logger = LoggerFactory.getLogger(GlavniController.class);

    @RequestMapping(value = {"/pocetna", "/"})
    public String prikaziPocetnuStranu(ModelMap model, @RequestParam(value = "success", required = false) String success) {
        logger.trace("method invoked: prikaziPocetnuStranu");
        if (success != null) {
            model.addAttribute("success", "Sistem je prijavio korisnika");
        }
        return "pocetna";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String prikaziAboutStranu() {
        logger.trace("method invoked: prikaziAboutStranu");
        return "about";
    }

    @RequestMapping(value = "/errorHandler")
    public String redirectToErrorHandlingPage(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        redirectAttributes.addFlashAttribute("statusCode", statusCode);
        return "redirect:/error";
    }


    @RequestMapping(value = "/error")
    public String showErrorPage() {
        return "errorHandlingPage";
    }

    @RequestMapping(value = "/kontakt", method = RequestMethod.GET)
    public String prikaziKontaktStranu() {
        logger.trace("method invoked: prikaziKontaktStranu");
        return "kontakt";
    }

    @RequestMapping(value = "/exception")
    public String showExceptionHandlingPage() {
        return "exceptionHandlingPage";
    }


}