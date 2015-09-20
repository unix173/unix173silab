package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;

import java.util.Date;
import java.util.List;

public interface VoziloService {

    List<Vozilo> getItems();

    Vozilo getItemById(String id);

    void addItem(Vozilo vozilo);

    void setItem(Vozilo vozilo);

    void deleteItem(Vozilo vozilo);

    List<Vozilo> getBestItemsForReservationFromItemTypeId(Long id, Integer quantity, Date startDate, Date endDate);
}
