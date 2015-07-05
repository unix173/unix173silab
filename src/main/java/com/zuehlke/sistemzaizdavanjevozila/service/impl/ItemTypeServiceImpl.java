package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.core.ItemTypeInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.dao.ItemDao;
import com.zuehlke.sistemzaizdavanjevozila.dao.ItemTypeDao;
import com.zuehlke.sistemzaizdavanjevozila.model.ItemType;
import com.zuehlke.sistemzaizdavanjevozila.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    @Autowired
    private ItemTypeDao itemTypeDao;

    @Autowired
    private ItemDao itemDao;

    @Override
    public void addItemType(ItemType itemType) {
        itemTypeDao.addItemType(itemType);
    }

    @Override
    public void setItemType(ItemType itemType) {
        itemTypeDao.setItemType(itemType);
    }

    @Override
    public void deleteItemType(ItemType itemType) {
        itemTypeDao.deleteItemType(itemType);
    }

    @Override
    public List<ItemType> getItemTypes() {
        return itemTypeDao.getItemTypes();
    }

    @Override
    public List<ItemTypeInfoDTO> getAvailableItemTypes(Set<Long> reservedItemTypeIdList, Date startDate, Date endDate) {

        List<ItemTypeInfoDTO> availableItemTypes = itemTypeDao.getAvailableItemTypes(startDate,endDate);
        List<ItemTypeInfoDTO> filteredAvailableItemTypes = new ArrayList<ItemTypeInfoDTO>();
        for(ItemTypeInfoDTO itemTypeInfoDTO : availableItemTypes) {
            if (!reservedItemTypeIdList.contains(itemTypeInfoDTO.getItemTypeId())) {
                filteredAvailableItemTypes.add(itemTypeInfoDTO);
            }
        }
        return filteredAvailableItemTypes;
    }

    @Override
    public ItemType getItemTypeById(Long id) {
        return itemTypeDao.getItemTypeById(id);
    }

    @Override
    public List<ItemType> getItemTypeByName(String name) {
        return itemTypeDao.getItemTypeByName(name);
    }
}
