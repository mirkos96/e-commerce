package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationUserRole;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.enumRole.EnumUserRole;
import com.maks.repository.model.UserRole;
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
@Qualifier("userRoleDao")
public class UserRoleDaoImpl implements ICrudOperation<UserRole, Long>,
        IAdditionalDatabaseOperationUserRole<UserRole, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(UserRole userRole) {
        getSession(sessionFactory).save(userRole);
    }

    @Transactional
    @Override
    public UserRole readEntity(Long id) {
        return getSession(sessionFactory).get(UserRole.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(UserRole userRole) {
        getSession(sessionFactory).saveOrUpdate(userRole);
    }

    @Transactional
    @Override
    public void deleteEntity(UserRole userRole) {
        getSession(sessionFactory).delete(userRole);
    }

    @Transactional
    @Override
    public List<UserRole> getUserRoleEntities() {
        Criteria criteria = getSession(sessionFactory).createCriteria(UserRole.class);
        return (List<UserRole>) criteria.list();
    }

    @Transactional
    @Override
    public UserRole getUserRoleByName(EnumUserRole userRole) {
        Criteria criteria = getSession(sessionFactory).createCriteria(UserRole.class);
        criteria.add(Restrictions.eq("userRole", userRole));
        return (UserRole) criteria.uniqueResult();
    }

    @Transactional
    @Override
    public UserRole getStandardUserRole() {
        Criteria criteria = getSession(sessionFactory).createCriteria(UserRole.class);
        criteria.add(Restrictions.eq("roleId", 1L));
        return (UserRole) criteria.uniqueResult();
    }
}
