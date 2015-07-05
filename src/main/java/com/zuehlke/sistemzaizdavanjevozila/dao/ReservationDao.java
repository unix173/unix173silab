package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.Reservation;

import java.util.List;

public interface ReservationDao {

    List<Reservation> getReservations();

    Reservation getReservationById(Long id);

    void addReservation(Reservation reservation);

    void setReservation(Reservation reservation);

    void deleteReservation(Reservation reservation);

    List<Reservation> getReservationsByUserId(Long id);

}
