package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationOrderItem;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.OrderItem;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Qualifier("orderItemDao")
public class OrderItemDaoImpl implements ICrudOperation<OrderItem, Long>,
        IAdditionalDatabaseOperationOrderItem<OrderItem, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderItemDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(OrderItem orderItem) {
        getSession(sessionFactory).save(orderItem);
    }

    @Transactional
    @Override
    public OrderItem readEntity(Long id) {
        return getSession(sessionFactory).get(OrderItem.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(OrderItem orderItem) {
        getSession(sessionFactory).saveOrUpdate(orderItem);
    }

    @Transactional
    @Override
    public void deleteEntity(OrderItem orderItem) {
        getSession(sessionFactory).delete(orderItem);
    }

    @Transactional
    @Override
    public List<OrderItem> getEntities() {
        Criteria criteria = getSession(sessionFactory).createCriteria(OrderItem.class);
        return criteria.list();
    }

    @Transactional
    @Override
    public void copyExistingEntity(Long id) {
        Session session = getSession(sessionFactory);
        OrderItem orderItem = session.get(OrderItem.class, id);
        session.detach(orderItem);
        session.save(orderItem);
    }
}
