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
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            HttpSession httpSession) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            AuthenticationException springSecurityLastException = (AuthenticationException)httpSession.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            logger.info("Login failed: AuthenticationException: " + springSecurityLastException.getMessage());
            model.addObject("error", "Pogrešno korisničko ime ili lozinka!");
        }

        if (logout != null) {
            logger.info("Successful login");
            model.addObject("msg", "Uspešno ste se odjavili.");
        }
        model.setViewName("login");

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
