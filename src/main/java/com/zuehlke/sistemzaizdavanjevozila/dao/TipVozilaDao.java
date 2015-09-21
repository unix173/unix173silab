package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.core.TipVozilaInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.model.TipVozila;

import java.util.Date;
import java.util.List;

public interface TipVozilaDao {

    List<TipVozila> getItemTypes();

    TipVozila getItemTypeById(Long id);

    void addItemType(TipVozila tipVozila);

    void setItemType(TipVozila tipVozila);

    List<TipVozila> getItemTypeByName(String name);

    void deleteItemType(TipVozila tipVozila);

    List<TipVozilaInfoDTO> getAvailableItemTypes(Date startDate, Date endDate);


}
