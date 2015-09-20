package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.form.AddReservationEntryForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import com.zuehlke.sistemzaizdavanjevozila.model.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> getReservations();

    Reservation getReservationById(Long id);

    void addReservation(Reservation reservation);

    void setReservation(Reservation reservation);

    void deleteReservation(Reservation reservation);

    List<Reservation> getReservationsByUserId(Long id);

    Reservation createReservation(List<AddReservationEntryForm> addReservationEntryForms, Korisnik korisnik);

}
