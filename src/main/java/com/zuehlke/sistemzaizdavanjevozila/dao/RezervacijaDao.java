package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.Rezervacija;

import java.util.List;

public interface RezervacijaDao {

    List<Rezervacija> getReservations();

    Rezervacija getReservationById(Long id);

    void addReservation(Rezervacija rezervacija);

    void setReservation(Rezervacija rezervacija);

    void deleteReservation(Rezervacija rezervacija);

    List<Rezervacija> getReservationsByUserId(Long id);

}
