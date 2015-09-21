package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.core.TipVozilaInfoDTO;
import com.zuehlke.sistemzaizdavanjevozila.model.TipVozila;

import java.util.Date;
import java.util.List;

public interface TipVozilaDao {

    List<TipVozila> vratiTipoveVozila();

    TipVozila ucitajTipVozilaID(Long id);

    void sacuvajTipVozila(TipVozila tipVozila);

    void izmeniTipVozila(TipVozila tipVozila);

    List<TipVozila> pretraziTipoveVozilaIme(String name);

    void obrisiTipVozila(TipVozila tipVozila);

    List<TipVozilaInfoDTO> vratiSlobdneTipoveVozila(Date startDate, Date endDate);


}
