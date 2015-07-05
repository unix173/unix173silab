package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.core.ItemTypeInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.model.ItemType;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ItemTypeService {
    
    List<ItemType> getItemTypes();

    ItemType getItemTypeById(Long id);

    void addItemType(ItemType itemType);

    void setItemType(ItemType itemType);

    List<ItemType> getItemTypeByName(String name);

    void deleteItemType(ItemType itemType);

    List<ItemTypeInfoDTO> getAvailableItemTypes(Set<Long> reservedItemTypeIdList, Date startDate, Date endDate);
}
