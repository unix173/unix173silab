package com.zuehlke.sistemzaizdavanjevozila.dao;

import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;

import java.util.List;

public interface StavkaRezervacijeDao {

    void sacuvajStavkuRezervacije(StavkaRezervacije stavkaRezervacije);

    List<StavkaRezervacije> vratiStavkeRezervacije();

    StavkaRezervacije ucitajStavkuRezervacijeID(Long id);

    void obrisiStavkuRezervacije(StavkaRezervacije stavkaRezervacije);

    void izmeniStavkuRezervacije(StavkaRezervacije stavkaRezervacije);
}
