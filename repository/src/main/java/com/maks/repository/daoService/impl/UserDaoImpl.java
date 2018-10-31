package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationUser;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.User;
import com.maks.repository.model.Verification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Qualifier("userDao")
public class UserDaoImpl implements ICrudOperation<User, Long>,
        IAdditionalDatabaseOperationUser<User, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void createEntity(User user) {
        getSession(sessionFactory).save(user);
    }

    @Transactional
    @Override
    public User readEntity(Long id) {
        return getSession(sessionFactory).get(User.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(User user) {
        getSession(sessionFactory).saveOrUpdate(user);
    }

    @Transactional
    @Override
    public void deleteEntity(User user) {
        getSession(sessionFactory).delete(user);
    }

    @Transactional
    @Override
    public User getByName(String name) {
        Criteria criteria = getSession(sessionFactory).createCriteria(User.class);
        criteria.add(Restrictions.eq("userLogin", name));
        return (User) criteria.uniqueResult();
    }

    @Transactional
    @Override
    public List<User> getListOfEntities() {
        Criteria criteria = getSession(sessionFactory).createCriteria(User.class);
        return (List<User>) criteria.list();
    }

    @Transactional
    @Override
    public void deleteById(Long userId) {
        User user = readEntity(userId);
        Session session = getSession(sessionFactory);
        session.delete(user);
    }

    @Transactional
    @Override
    public User getByVerificationToken(Verification verification) {
        Criteria criteria = getSession(sessionFactory).createCriteria(User.class);
        criteria.add(Restrictions.eq("verification", verification));
        return (User) criteria.uniqueResult();
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }
}
