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
        stavkaRezervacijeDao.addReservationEntry(stavkaRezervacije);
    }

    @Override
    public List<StavkaRezervacije> getReservationEntries() {
        return stavkaRezervacijeDao.getReservationEntries();
    }

    @Override
    public StavkaRezervacije getReservationEntryById(Long id) {
        return stavkaRezervacijeDao.getReservationEntryById(id);
    }

    @Override
    public void deleteReservationEntry(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacijeDao.addReservationEntry(stavkaRezervacije);
    }

    @Override
    public void setReservationEntry(StavkaRezervacije stavkaRezervacije) {
        stavkaRezervacijeDao.setReservationEntry(stavkaRezervacije);
    }
}
