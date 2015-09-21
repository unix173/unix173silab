package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.core.TipVozilaInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.model.TipVozila;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TipVozilaService {
    
    List<TipVozila> vratiTipoveVozila();

    TipVozila ucitajTipVozilaID(Long id);

    void sacuvajTipVozila(TipVozila tipVozila);

    void izmeniTipVozila(TipVozila tipVozila);

    List<TipVozila> pretraziVozilaPoImenu(String name);

    void obrisiTipVozila(TipVozila tipVozila);

    List<TipVozilaInfoDTO> vratiSlobodneTipoveVozila(Set<Long> reservedItemTypeIdList, Date startDate, Date endDate);
}
