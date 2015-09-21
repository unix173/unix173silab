package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.VoziloDao;
import com.zuehlke.sistemzaizdavanjevozila.model.Vozilo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class VoziloDaoImpl implements VoziloDao {

    private SessionFactory sessionFactory;

    @Autowired
    public VoziloDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Vozilo> vratiVozila() {
        return sessionFactory.getCurrentSession().createCriteria(Vozilo.class).list();
    }

    @Override
    public Vozilo ucitajVoziloID(String id) {
        return (Vozilo) sessionFactory.getCurrentSession().get(Vozilo.class, id);
    }

    @Override
    public void sacuvajVozilo(Vozilo vozilo) {
        sessionFactory.getCurrentSession().save(vozilo);
    }

    @Override
    public void izmeniVozilo(Vozilo vozilo) {
        sessionFactory.getCurrentSession().update(vozilo);
    }

    @Override
    public void obrisiVozilo(Vozilo vozilo) {
        sessionFactory.getCurrentSession().delete(vozilo);
    }

    @Override
    public List<Vozilo> vratiSlobodnaVozila(Long itemTypeId, String startDate, String endDate) {
        String query = "from Vozilo i " +
                "where i.tipVozila.id = :itemTypeId and i.id not in " +
                "(select distinct i.id " +
                "from StavkaRezervacije  re right outer join re.vozilo i " +
                "where (re.reservationStartDate <= :endDate and re.reservationEndDate >= :startDate))";

            List<Vozilo> vozila =
                    sessionFactory.getCurrentSession().createQuery(query)
                            .setString("startDate", startDate)
                            .setString("endDate", endDate)
                            .setLong("itemTypeId", itemTypeId)
                            .list();
            return vozila;
        }
}



