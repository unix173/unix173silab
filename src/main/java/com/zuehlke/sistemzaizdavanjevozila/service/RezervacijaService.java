package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.form.AddReservationEntryForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.model.Rezervacija;

import java.util.List;

public interface RezervacijaService {

    List<Rezervacija> getReservations();

    Rezervacija getReservationById(Long id);

    void addReservation(Rezervacija rezervacija);

    void setReservation(Rezervacija rezervacija);

    void deleteReservation(Rezervacija rezervacija);

    List<Rezervacija> getReservationsByUserId(Long id);

    Rezervacija createReservation(List<AddReservationEntryForm> addReservationEntryForms, Korisnik korisnik);

}
