package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.KorisnikDao;
import com.zuehlke.sistemzaizdavanjevozila.model.Korisnik;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class KorisnikDaoImpl implements KorisnikDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void sacuvajKorisnika(Korisnik korisnik) {
        sessionFactory.getCurrentSession().persist(korisnik);
    }

    @Override
    public List<Korisnik> vratiKorisnike() {
        return sessionFactory.getCurrentSession().createCriteria(Korisnik.class).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public Korisnik ucitajKorisnikaID(Long id) {
        return (Korisnik) sessionFactory.getCurrentSession().get(Korisnik.class, id);
    }

    @Override
    public Korisnik ucitajKorisnikaUsername(String username) {
        return (Korisnik) sessionFactory.getCurrentSession().createQuery("FROM Korisnik u where u.username = :username")
                .setString("username", username)
                .uniqueResult();
    }

    @Override
    public Korisnik ucitajKorisnikaEmail(String email) {
        return (Korisnik) sessionFactory.getCurrentSession().createQuery("FROM Korisnik u where u.email = :email")
                .setString("email", email)
                .uniqueResult();
    }

    @Override
    public void obrisiKorisnika(Korisnik korisnik) {
        sessionFactory.getCurrentSession().delete(korisnik);
    }

    @Override
    public void izmeniKorisnika(Korisnik korisnik) {
        sessionFactory.getCurrentSession().update(korisnik);
    }

    @Override
    public List<Korisnik> pretraziKorisnikeUsername(String name) {
        return sessionFactory.getCurrentSession().getNamedQuery("pretraziKorisnikeUsername").setString("name", "%" + name + "%").setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public Korisnik ucitajKorisnikaConfID(String confirmationId) {
        return (Korisnik) sessionFactory.getCurrentSession().createQuery("FROM Korisnik u where u.confirmationId = :confirmationId")
                .setString("confirmationId", confirmationId)
                .uniqueResult();
    }
}
