package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;

import java.util.List;

public interface VoziloDao {

    List<Vozilo> vratiVozila();

    Vozilo ucitajVoziloID(String id);

    void sacuvajVozilo(Vozilo vozilo);

    void izmeniVozilo(Vozilo vozilo);

    void obrisiVozilo(Vozilo vozilo);

    List<Vozilo> vratiSlobodnaVozila(Long itemTypeId, String startDate, String endDate);
}
