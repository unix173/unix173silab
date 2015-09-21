package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.Rezervacija;

import java.util.List;

public interface RezervacijaDao {

    List<Rezervacija> vratiRezervacije();

    Rezervacija ucitajRezervacijuID(Long id);

    void sacuvajRezervaciju(Rezervacija rezervacija);

    void izmeniRezervaciju(Rezervacija rezervacija);

    void obrisiRezervaciju(Rezervacija rezervacija);

    List<Rezervacija> ucitajRezervacijeKorisnik(Long id);

}
