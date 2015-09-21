package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.form.DodajStavkuRezervacijeForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.model.Rezervacija;

import java.util.List;

public interface RezervacijaService {

    List<Rezervacija> vratiRezervacije();

    Rezervacija ucitajRezervacijuID(Long id);

    void sacuvajRezervaciju(Rezervacija rezervacija);

    void izmeniRezervaciju(Rezervacija rezervacija);

    void obrisiRezervaciju(Rezervacija rezervacija);

    List<Rezervacija> vratiRezervacijeUserID(Long id);

    Rezervacija kreirajRezervaciju(List<DodajStavkuRezervacijeForm> dodajStavkuRezervacijeForms, Korisnik korisnik);

}
