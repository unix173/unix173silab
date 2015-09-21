package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;

import java.util.Date;
import java.util.List;

public interface VoziloService {

    List<Vozilo> vratiVozila();

    Vozilo ucitajVoziloID(String id);

    void sacuvajVozilo(Vozilo vozilo);

    void izmeniVozilo(Vozilo vozilo);

    void obrisiVozilo(Vozilo vozilo);

    List<Vozilo> vratiAdekvatnaVozilaZaRezervisanje(Long id, Integer quantity, Date startDate, Date endDate);
}
