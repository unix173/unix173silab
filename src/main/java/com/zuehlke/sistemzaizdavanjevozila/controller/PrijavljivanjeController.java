package com.zuehlke.sistemzaizdavanjevozila.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PrijavljivanjeController {

    private final static Logger logger = LoggerFactory.getLogger(PrijavljivanjeController.class);

    @RequestMapping(value = "/prijavljivanje", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "registered", required = false) String registered,
            HttpSession httpSession) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            AuthenticationException springSecurityLastException = (AuthenticationException)httpSession.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            logger.info("Login failed: AuthenticationException: " + springSecurityLastException.getMessage());
            model.addObject("error", "Sistem ne može da prijavi korisnika!");
        }
        if (logout != null) {
            logger.info("Successful login");
            model.addObject("msg", "Sistem je odjavio korisnika.");
        }
        if (registered != null) {
            logger.info("Successful registered");
            model.addObject("msg", "Sistem je kreirao korisnika.");
        }
        model.setViewName("prijavljivanje");

        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        logger.info("Successful logout");
        return "logout";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedRedirect() {
        logger.info("Access denied");
        return "redirect:/";
    }

}
