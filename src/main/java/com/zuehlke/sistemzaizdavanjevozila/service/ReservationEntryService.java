package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.model.ReservationEntry;

import java.util.List;

public interface ReservationEntryService {

    void addReservationEntry(ReservationEntry reservationEntry);

    List<ReservationEntry> getReservationEntries();

    ReservationEntry getReservationEntryById(Long id);

    void deleteReservationEntry(ReservationEntry reservationEntry);

    void setReservationEntry(ReservationEntry reservationEntry);
}
