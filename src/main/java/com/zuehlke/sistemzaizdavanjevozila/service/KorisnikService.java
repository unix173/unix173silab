package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.form.IzmenaLozinkeForm;
import com.zuehlke.sistemzaizdavanjevozila.form.RegistracijaKorisnikaForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;

import java.util.List;

public interface KorisnikService {

    void addUser(RegistracijaKorisnikaForm registracijaKorisnikaForm, String confirmationId);

    List<Korisnik> getUsers();

    Korisnik getUserById(Long id);

    Korisnik getUserByUsername(String username);

    void deleteUser(Korisnik korisnik);

    void changePassword(IzmenaLozinkeForm izmenaLozinkeForm);

    boolean checkIfUnique(RegistracijaKorisnikaForm registracijaKorisnikaForm);

    boolean checkIfPasswordIsCorrect(Long id, String oldPassword);

    List<Korisnik> getUsersByUsername(String username);

    boolean confirmRegistration(String confirmationId);
}
