package com.zuehlke.sistemzaizdavanjevozila.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public String redirectToExceptionHandlingPage(Exception e){
        logger.error("Exception occured");
        logger.error(e.getMessage());
        for(StackTraceElement stackTraceElement : e.getStackTrace()){
            logger.error(stackTraceElement.toString());
        }
        return "redirect:/exception";
    }

}
