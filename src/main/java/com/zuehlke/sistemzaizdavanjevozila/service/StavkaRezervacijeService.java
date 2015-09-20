package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;

import java.util.List;

public interface StavkaRezervacijeService {

    void addReservationEntry(StavkaRezervacije stavkaRezervacije);

    List<StavkaRezervacije> getReservationEntries();

    StavkaRezervacije getReservationEntryById(Long id);

    void deleteReservationEntry(StavkaRezervacije stavkaRezervacije);

    void setReservationEntry(StavkaRezervacije stavkaRezervacije);
}
