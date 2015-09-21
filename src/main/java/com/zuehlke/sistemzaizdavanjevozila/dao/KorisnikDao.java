package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;

import java.util.List;

public interface KorisnikDao {

    void sacuvajKorisnika(Korisnik korisnik);

    List<Korisnik> vratiKorisnike();

    Korisnik ucitajKorisnikaID(Long id);

    Korisnik ucitajKorisnikaUsername(String username);

    Korisnik ucitajKorisnikaEmail(String email);

    void obrisiKorisnika(Korisnik korisnik);

    void izmeniKorisnika(Korisnik korisnik);

    List<Korisnik> pretraziKorisnikeUsername(String username);

    Korisnik ucitajKorisnikaConfID(String confirmationId);
}
