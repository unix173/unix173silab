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
    public void addUser(Korisnik korisnik) {
        sessionFactory.getCurrentSession().persist(korisnik);
    }

    @Override
    public List<Korisnik> getUsers() {
        return sessionFactory.getCurrentSession().createCriteria(Korisnik.class).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public Korisnik getUserById(Long id) {
        return (Korisnik) sessionFactory.getCurrentSession().get(Korisnik.class, id);
    }

    @Override
    public Korisnik getUserByUsername(String username) {
        return (Korisnik) sessionFactory.getCurrentSession().createQuery("FROM Korisnik u where u.username = :username")
                .setString("username", username)
                .uniqueResult();
    }

    @Override
    public Korisnik getUserByEmail(String email) {
        return (Korisnik) sessionFactory.getCurrentSession().createQuery("FROM Korisnik u where u.email = :email")
                .setString("email", email)
                .uniqueResult();
    }

    @Override
    public void deleteUser(Korisnik korisnik) {
        sessionFactory.getCurrentSession().delete(korisnik);
    }

    @Override
    public void setUser(Korisnik korisnik) {
        sessionFactory.getCurrentSession().update(korisnik);
    }

    @Override
    public List<Korisnik> getUsersByUsername(String name) {
        return sessionFactory.getCurrentSession().getNamedQuery("getUsersByUsername").setString("name", "%" + name + "%").setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public Korisnik getUserByConfirmationId(String confirmationId) {
        return (Korisnik) sessionFactory.getCurrentSession().createQuery("FROM Korisnik u where u.confirmationId = :confirmationId")
                .setString("confirmationId", confirmationId)
                .uniqueResult();
    }
}
