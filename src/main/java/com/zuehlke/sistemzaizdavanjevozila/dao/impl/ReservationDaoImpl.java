package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.ReservationDao;
import com.zuehlke.sistemzaizdavanjevozila.model.Reservation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReservationDaoImpl implements ReservationDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ReservationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Reservation> getReservations() {
        return sessionFactory.getCurrentSession().createCriteria(Reservation.class).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return (Reservation) sessionFactory.getCurrentSession().get(Reservation.class, id);
    }

    @Override
    public void addReservation(Reservation reservation) {
        sessionFactory.getCurrentSession().save(reservation);
    }

    @Override
    public void setReservation(Reservation reservation) {
        sessionFactory.getCurrentSession().update(reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        sessionFactory.getCurrentSession().delete(reservation);
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long id) {
        return sessionFactory.getCurrentSession().getNamedQuery("getReservationsByUserId").setLong("userid", id).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }
}
