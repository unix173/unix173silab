package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.Item;

import java.util.List;

public interface ItemDao {

    List<Item> getItems();

    Item getItemById(String id);

    void addItem(Item item);

    void setItem(Item item);

    void deleteItem(Item item);

    List<Item> getAvailableItemsOfItemType(Long itemTypeId, String startDate, String endDate);
}
