package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.ReservationEntryDao;
import com.zuehlke.sistemzaizdavanjevozila.model.ReservationEntry;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReservationEntryDaoImpl implements ReservationEntryDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ReservationEntryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addReservationEntry(ReservationEntry reservationEntry) {
        sessionFactory.getCurrentSession().save(reservationEntry);
    }

    @Override
    public List<ReservationEntry> getReservationEntries() {
        return sessionFactory.getCurrentSession().createCriteria(ReservationEntry.class).list();
    }

    @Override
    public ReservationEntry getReservationEntryById(Long id) {
        return (ReservationEntry) sessionFactory.getCurrentSession().get(ReservationEntry.class, id);
    }

    @Override
    public void deleteReservationEntry(ReservationEntry reservationEntry) {
        sessionFactory.getCurrentSession().delete(reservationEntry);
    }

    @Override
    public void setReservationEntry(ReservationEntry reservationEntry) {
        sessionFactory.getCurrentSession().update(reservationEntry);
    }
}
