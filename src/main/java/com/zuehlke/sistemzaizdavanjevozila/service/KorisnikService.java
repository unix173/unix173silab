package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.form.ChangePasswordForm;
import com.zuehlke.sistemzaizdavanjevozila.form.UserRegistrationForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;

import java.util.List;

public interface KorisnikService {

    void addUser(UserRegistrationForm userRegistrationForm, String confirmationId);

    List<Korisnik> getUsers();

    Korisnik getUserById(Long id);

    Korisnik getUserByUsername(String username);

    void deleteUser(Korisnik korisnik);

    void changePassword(ChangePasswordForm changePasswordForm);

    boolean checkIfUnique(UserRegistrationForm userRegistrationForm);

    boolean checkIfPasswordIsCorrect(Long id, String oldPassword);

    List<Korisnik> getUsersByUsername(String username);

    boolean confirmRegistration(String confirmationId);
}
