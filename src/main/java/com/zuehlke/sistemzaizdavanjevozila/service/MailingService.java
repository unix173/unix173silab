package com.zuehlke.sistemzaizdavanjevozila.service;

public interface MailingService {

    void sendMail(String to, String subject, String body);

    void sendPreConfiguredMail(String message);

}
