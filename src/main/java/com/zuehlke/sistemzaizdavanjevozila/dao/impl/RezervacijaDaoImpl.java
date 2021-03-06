package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.RezervacijaDao;
import com.zuehlke.sistemzaizdavanjevozila.model.Rezervacija;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RezervacijaDaoImpl implements RezervacijaDao {

    private SessionFactory sessionFactory;

    @Autowired
    public RezervacijaDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Rezervacija> vratiRezervacije() {
        return sessionFactory.getCurrentSession().createCriteria(Rezervacija.class).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public Rezervacija ucitajRezervacijuID(Long id) {
        return (Rezervacija) sessionFactory.getCurrentSession().get(Rezervacija.class, id);
    }

    @Override
    public void sacuvajRezervaciju(Rezervacija rezervacija) {
        sessionFactory.getCurrentSession().save(rezervacija);
    }

    @Override
    public void izmeniRezervaciju(Rezervacija rezervacija) {
        sessionFactory.getCurrentSession().update(rezervacija);
    }

    @Override
    public void obrisiRezervaciju(Rezervacija rezervacija) {
        sessionFactory.getCurrentSession().delete(rezervacija);
    }

    @Override
    public List<Rezervacija> ucitajRezervacijeKorisnik(Long id) {
        return sessionFactory.getCurrentSession().getNamedQuery("ucitajRezervacijeKorisnika").setLong("userid", id).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }
}
