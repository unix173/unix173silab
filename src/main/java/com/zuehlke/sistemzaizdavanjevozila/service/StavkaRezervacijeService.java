package com.zuehlke.sistemzaizdavanjevozila.service;

import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;

import java.util.List;

public interface StavkaRezervacijeService {

    void sacuvajStavkuRezervacije(StavkaRezervacije stavkaRezervacije);

    List<StavkaRezervacije> vratiStavkeRezervacije();

    StavkaRezervacije ucitajStavkuRezervacijeID(Long id);

    void obrisiStavkuRezervacije(StavkaRezervacije stavkaRezervacije);

    void izmeniStavkuRezervacije(StavkaRezervacije stavkaRezervacije);
}
