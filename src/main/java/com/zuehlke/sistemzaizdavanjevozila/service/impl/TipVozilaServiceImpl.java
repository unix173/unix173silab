package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.core.TipVozilaInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.dao.VoziloDao;
import com.zuehlke.sistemzaizdavanjevozila.dao.TipVozilaDao;
import com.zuehlke.sistemzaizdavanjevozila.model.TipVozila;
import com.zuehlke.sistemzaizdavanjevozila.service.TipVozilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class TipVozilaServiceImpl implements TipVozilaService {

    @Autowired
    private TipVozilaDao tipVozilaDao;

    @Autowired
    private VoziloDao voziloDao;

    @Override
    public void addItemType(TipVozila tipVozila) {
        tipVozilaDao.sacuvajTipVozila(tipVozila);
    }

    @Override
    public void setItemType(TipVozila tipVozila) {
        tipVozilaDao.izmeniTipVozila(tipVozila);
    }

    @Override
    public void deleteItemType(TipVozila tipVozila) {
        tipVozilaDao.obrisiTipVozila(tipVozila);
    }

    @Override
    public List<TipVozila> getItemTypes() {
        return tipVozilaDao.vratiTipoveVozila();
    }

    @Override
    public List<TipVozilaInfoDTO> getAvailableItemTypes(Set<Long> reservedItemTypeIdList, Date startDate, Date endDate) {

        List<TipVozilaInfoDTO> availableItemTypes = tipVozilaDao.vratiSlobdneTipoveVozila(startDate, endDate);
        List<TipVozilaInfoDTO> filteredAvailableItemTypes = new ArrayList<TipVozilaInfoDTO>();
        for(TipVozilaInfoDTO tipVozilaInfoDTO : availableItemTypes) {
            if (!reservedItemTypeIdList.contains(tipVozilaInfoDTO.getItemTypeId())) {
                filteredAvailableItemTypes.add(tipVozilaInfoDTO);
            }
        }
        return filteredAvailableItemTypes;
    }

    @Override
    public TipVozila getItemTypeById(Long id) {
        return tipVozilaDao.ucitajTipVozilaID(id);
    }

    @Override
    public List<TipVozila> getItemTypeByName(String name) {
        return tipVozilaDao.pretraziTipoveVozilaIme(name);
    }
}
