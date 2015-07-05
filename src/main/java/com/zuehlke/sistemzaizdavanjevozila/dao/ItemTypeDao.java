package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.core.ItemTypeInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.model.ItemType;

import java.util.Date;
import java.util.List;

public interface ItemTypeDao {

    List<ItemType> getItemTypes();

    ItemType getItemTypeById(Long id);

    void addItemType(ItemType itemType);

    void setItemType(ItemType itemType);

    List<ItemType> getItemTypeByName(String name);

    void deleteItemType(ItemType itemType);

    List<ItemTypeInfoDTO> getAvailableItemTypes(Date startDate, Date endDate);


}
