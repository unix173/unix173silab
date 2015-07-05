package com.zuehlke.sistemzaizdavanjevozila.service.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.ReservationEntryDao;
import com.zuehlke.sistemzaizdavanjevozila.model.ReservationEntry;
import com.zuehlke.sistemzaizdavanjevozila.service.ReservationEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationEntryServiceImpl implements ReservationEntryService {

    @Autowired
    private ReservationEntryDao reservationEntryDao;

    @Override
    public void addReservationEntry(ReservationEntry reservationEntry) {
        reservationEntryDao.addReservationEntry(reservationEntry);
    }

    @Override
    public List<ReservationEntry> getReservationEntries() {
        return reservationEntryDao.getReservationEntries();
    }

    @Override
    public ReservationEntry getReservationEntryById(Long id) {
        return reservationEntryDao.getReservationEntryById(id);
    }

    @Override
    public void deleteReservationEntry(ReservationEntry reservationEntry) {
        reservationEntryDao.addReservationEntry(reservationEntry);
    }

    @Override
    public void setReservationEntry(ReservationEntry reservationEntry) {
        reservationEntryDao.setReservationEntry(reservationEntry);
    }
}
