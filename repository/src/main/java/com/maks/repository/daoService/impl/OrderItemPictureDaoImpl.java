package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationItemPicture;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.OrderItemPicture;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Qualifier("orderItemPictureDao")
public class OrderItemPictureDaoImpl implements ICrudOperation<OrderItemPicture, Long>,
        IAdditionalDatabaseOperationItemPicture<OrderItemPicture, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderItemPictureDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(OrderItemPicture orderItemPicture) {
        getSession(sessionFactory).save(orderItemPicture);
    }

    @Transactional
    @Override
    public OrderItemPicture readEntity(Long id) {
        return getSession(sessionFactory).get(OrderItemPicture.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(OrderItemPicture orderItemPicture) {
        getSession(sessionFactory).saveOrUpdate(orderItemPicture);
    }

    @Transactional
    @Override
    public void deleteEntity(OrderItemPicture orderItemPicture) {
        getSession(sessionFactory).delete(orderItemPicture);
    }

    @Transactional
    @Override
    public OrderItemPicture getByName(String name) {
        Criteria criteria = getSession(sessionFactory).createCriteria(OrderItemPicture.class);
        criteria.add(Restrictions.eq("pictureName", name));
        return (OrderItemPicture) criteria.uniqueResult();
    }
}
