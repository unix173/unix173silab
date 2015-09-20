package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;

import java.util.List;

public interface VoziloDao {

    List<Vozilo> getItems();

    Vozilo getItemById(String id);

    void addItem(Vozilo vozilo);

    void setItem(Vozilo vozilo);

    void deleteItem(Vozilo vozilo);

    List<Vozilo> getAvailableItemsOfItemType(Long itemTypeId, String startDate, String endDate);
}
