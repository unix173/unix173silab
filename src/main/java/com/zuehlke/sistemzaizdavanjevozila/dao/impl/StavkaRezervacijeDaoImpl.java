package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.StavkaRezervacijeDao;
import com.zuehlke.sistemzaizdavanjevozila.model.StavkaRezervacije;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StavkaRezervacijeDaoImpl implements StavkaRezervacijeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public StavkaRezervacijeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void sacuvajStavkuRezervacije(StavkaRezervacije stavkaRezervacije) {
        sessionFactory.getCurrentSession().save(stavkaRezervacije);
    }

    @Override
    public List<StavkaRezervacije> vratiStavkeRezervacije() {
        return sessionFactory.getCurrentSession().createCriteria(StavkaRezervacije.class).list();
    }

    @Override
    public StavkaRezervacije ucitajStavkuRezervacijeID(Long id) {
        return (StavkaRezervacije) sessionFactory.getCurrentSession().get(StavkaRezervacije.class, id);
    }

    @Override
    public void obrisiStavkuRezervacije(StavkaRezervacije stavkaRezervacije) {
        sessionFactory.getCurrentSession().delete(stavkaRezervacije);
    }

    @Override
    public void izmeniStavkuRezervacije(StavkaRezervacije stavkaRezervacije) {
        sessionFactory.getCurrentSession().update(stavkaRezervacije);
    }
}
