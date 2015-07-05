package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.model.Item;

import java.util.Date;
import java.util.List;

public interface ItemService {

    List<Item> getItems();

    Item getItemById(String id);

    void addItem(Item item);

    void setItem(Item item);

    void deleteItem(Item item);

    List<Item> getBestItemsForReservationFromItemTypeId(Long id, Integer quantity, Date startDate, Date endDate);
}
