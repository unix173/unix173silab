package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.form.IzmenaLozinkeForm;
import com.zuehlke.sistemzaizdavanjevozila.form.RegistracijaKorisnikaForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;

import java.util.List;

public interface KorisnikService {

    void sacuvajKorisnika(RegistracijaKorisnikaForm registracijaKorisnikaForm, String confirmationId);

    List<Korisnik> vratiKorisnike();

    Korisnik ucitajKorisnikaID(Long id);

    Korisnik ucitajKorisnikaUsername(String username);

    void obrisiKorisnika(Korisnik korisnik);

    void izmeniKorisnika(IzmenaLozinkeForm izmenaLozinkeForm);

    boolean checkIfUnique(RegistracijaKorisnikaForm registracijaKorisnikaForm);

    boolean checkIfPasswordIsCorrect(Long id, String oldPassword);

    List<Korisnik> pretraziKorisnikeUserName(String username);

    boolean confirmRegistration(String confirmationId);
}
