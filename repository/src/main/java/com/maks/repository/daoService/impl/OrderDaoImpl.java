package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationOrder;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.Order;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Qualifier("orderDao")
public class OrderDaoImpl implements ICrudOperation<Order, Long>,
        IAdditionalDatabaseOperationOrder<Order, Long> {

    private final SessionFactory sessionFactory;
    private final ICrudOperation userDao;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory,
                        @Qualifier("userDao")
                            ICrudOperation userDao) {
        this.sessionFactory = sessionFactory;
        this.userDao = userDao;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(Order order) {
        getSession(sessionFactory).save(order);
    }

    @Transactional
    @Override
    public Order readEntity(Long id) {
        return getSession(sessionFactory).get(Order.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(Order order) {
        getSession(sessionFactory).saveOrUpdate(order);
    }

    @Transactional
    @Override
    public void deleteEntity(Order order) {
        getSession(sessionFactory).delete(order);
    }

    @Transactional
    @Override
    public List<Order> getEntities(Long id) {
        Criteria criteria = getSession(sessionFactory).createCriteria(Order.class);
        criteria.add(Restrictions.eq("user", userDao.readEntity(id)));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Order> orderList = criteria.list();
        for (Order order : orderList) {
            Hibernate.initialize(order.getOrderOrderItemList());
        }
        return orderList;
    }

    @Transactional
    @Override
    public List<Order> getEntities() {
        Criteria criteria = getSession(sessionFactory).createCriteria(Order.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Order> orderList = criteria.list();
        for (Order order : orderList) {
            Hibernate.initialize(order.getOrderStatus());
            Hibernate.initialize(order.getOrderOrderItemList());
        }
        return orderList;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        String hql = "delete from Order where id = :ID";
        getSession(sessionFactory).createQuery(hql).
                setParameter("ID", id).executeUpdate();
    }
}
