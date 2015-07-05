package com.zuehlke.sistemzaizdavanjevozila.dao.impl;

import com.zuehlke.sistemzaizdavanjevozila.dao.UserDao;
import com.zuehlke.sistemzaizdavanjevozila.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public User getUserById(Long id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) sessionFactory.getCurrentSession().createQuery("FROM User u where u.username = :username")
                .setString("username", username)
                .uniqueResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return (User) sessionFactory.getCurrentSession().createQuery("FROM User u where u.email = :email")
                .setString("email", email)
                .uniqueResult();
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void setUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public List<User> getUsersByUsername(String name) {
        return sessionFactory.getCurrentSession().getNamedQuery("getUsersByUsername").setString("name", "%" + name + "%").setResultTransformer((Criteria.DISTINCT_ROOT_ENTITY)).list();
    }

    @Override
    public User getUserByConfirmationId(String confirmationId) {
        return (User) sessionFactory.getCurrentSession().createQuery("FROM User u where u.confirmationId = :confirmationId")
                .setString("confirmationId", confirmationId)
                .uniqueResult();
    }
}
