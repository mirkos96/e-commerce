package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationVerification;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.Verification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Qualifier("verificationDao")
public class VerificationDaoImpl implements ICrudOperation<Verification, Long>,
        IAdditionalDatabaseOperationVerification<Verification, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public VerificationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(Verification verification) {
        getSession(sessionFactory).save(verification);
    }

    @Transactional
    @Override
    public Verification readEntity(Long id) {
        return getSession(sessionFactory).get(Verification.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(Verification verification) {
        getSession(sessionFactory).saveOrUpdate(verification);
    }

    @Transactional
    @Override
    public void deleteEntity(Verification verification) {
        getSession(sessionFactory).delete(verification);
    }

    @Transactional
    @Override
    public Verification getVerificationByToken(String verificationToken) {
        Criteria criteria = getSession(sessionFactory).createCriteria(Verification.class);
        criteria.add(Restrictions.eq("verificationToken", verificationToken));
        return (Verification) criteria.uniqueResult();
    }
}
