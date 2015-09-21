package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.StavkaRezervacijeDao;
import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;
import com.zuehlke.sistemzaizdavanjevozila.service.StavkaRezervacijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StavkaRezervacijeServiceImpl implements StavkaRezervacijeService {

    @Autowired
    private StavkaRezervacijeDao stavkaRezervacijeDao;

    @Override
    public void sacuvajStavkuRezervacije(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacijeDao.sacuvajStavkuRezervacije(stavkaRezervacije);
    }

    @Override
    public List<StavkaRezervacije> vratiStavkeRezervacije() {
        return stavkaRezervacijeDao.vratiStavkeRezervacije();
    }

    @Override
    public StavkaRezervacije ucitajStavkuRezervacijeID(Long id) {
        return stavkaRezervacijeDao.ucitajStavkuRezervacijeID(id);
    }

    @Override
    public void obrisiStavkuRezervacije(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacijeDao.sacuvajStavkuRezervacije(stavkaRezervacije);
    }

    @Override
    public void izmeniStavkuRezervacije(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacijeDao.izmeniStavkuRezervacije(stavkaRezervacije);
    }
}
