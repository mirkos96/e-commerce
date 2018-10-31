package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationOrderStatus;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.enumRole.EnumOrderStatus;
import com.maks.repository.model.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Qualifier("orderStatusDao")
public class OrderStatusDaoImpl implements ICrudOperation<OrderStatus, Long>,
        IAdditionalDatabaseOperationOrderStatus<OrderStatus, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderStatusDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(OrderStatus orderStatus) {
        getSession(sessionFactory).save(orderStatus);
    }

    @Transactional
    @Override
    public OrderStatus readEntity(Long id) {
        return getSession(sessionFactory).get(OrderStatus.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(OrderStatus orderStatus) {
        getSession(sessionFactory).saveOrUpdate(orderStatus);
    }

    @Transactional
    @Override
    public void deleteEntity(OrderStatus orderStatus) {
        getSession(sessionFactory).delete(orderStatus);
    }

    @Transactional
    @Override
    public OrderStatus getByName(EnumOrderStatus orderStatusName) {
        Criteria criteria = getSession(sessionFactory).createCriteria(OrderStatus.class);
        criteria.add(Restrictions.eq("orderStatus", orderStatusName));
        return (OrderStatus) criteria.uniqueResult();
    }

    @Transactional
    @Override
    public OrderStatus getStandardStatus() {
        Criteria criteria = getSession(sessionFactory).createCriteria(OrderStatus.class);
        criteria.add(Restrictions.eq("statusId", 1L));
        return (OrderStatus) criteria.uniqueResult();
    }
}
