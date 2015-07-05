package com.zuehlke.sistemzaizdavanjevozila.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    private final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = {"/home", "/"})
    public String showHomeView(ModelMap model) {
        logger.trace("method invoked: showHomeView");
        return "home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showAboutView() {
        logger.trace("method invoked: showAboutView");
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

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String showContactView() {
        logger.trace("method invoked: showContactView");
        return "contact";
    }

    @RequestMapping(value = "/exception")
    public String showExceptionHandlingPage() {
        return "exceptionHandlingPage";
    }


}