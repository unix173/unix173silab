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
    public void addReservationEntry(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacijeDao.sacuvajStavkuRezervacije(stavkaRezervacije);
    }

    @Override
    public List<StavkaRezervacije> getReservationEntries() {
        return stavkaRezervacijeDao.vratiStavkeRezervacije();
    }

    @Override
    public StavkaRezervacije getReservationEntryById(Long id) {
        return stavkaRezervacijeDao.ucitajStavkuRezervacijeID(id);
    }

    @Override
    public void deleteReservationEntry(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacijeDao.sacuvajStavkuRezervacije(stavkaRezervacije);
    }

    @Override
    public void setReservationEntry(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacijeDao.izmeniStavkuRezervacije(stavkaRezervacije);
    }
}
