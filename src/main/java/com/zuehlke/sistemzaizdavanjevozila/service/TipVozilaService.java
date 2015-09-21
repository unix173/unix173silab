package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.core.TipVozilaInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.model.TipVozila;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TipVozilaService {
    
    List<TipVozila> getItemTypes();

    TipVozila getItemTypeById(Long id);

    void addItemType(TipVozila tipVozila);

    void setItemType(TipVozila tipVozila);

    List<TipVozila> getItemTypeByName(String name);

    void deleteItemType(TipVozila tipVozila);

    List<TipVozilaInfoDTO> getAvailableItemTypes(Set<Long> reservedItemTypeIdList, Date startDate, Date endDate);
}
