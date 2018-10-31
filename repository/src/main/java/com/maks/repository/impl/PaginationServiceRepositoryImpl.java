package com.maks.repository.impl;

import com.maks.repository.IPaginationServiceRepository;
import com.maks.repository.model.OrderItem;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PaginationServiceRepositoryImpl
        implements IPaginationServiceRepository {

    public static final Logger log = Logger.getLogger(PaginationServiceRepositoryImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public PaginationServiceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public List<OrderItem> getCertainAmountOfOrderItemsForPage
            (Integer currentPageNumber) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(OrderItem.class);
        Integer itemsPerPage = 3;
        Integer firstResult;
        if (currentPageNumber == 1) {
            firstResult = 0;
        } else {
            firstResult = (currentPageNumber * itemsPerPage) - itemsPerPage;
        }
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(itemsPerPage);
        List<OrderItem> orderItemList = criteria.list();
        for (OrderItem item : orderItemList) {
            log.info("Name of the orderItem: " + item.getOrderName());
        }
        return orderItemList;
    }

    @Transactional
    @Override
    public Integer countAmountOfPages() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(OrderItem.class);
        Integer amountOfItems = criteria.list().size();
        if (amountOfItems % 3 == 0) {
            return amountOfItems / 3;
        } else
            return (amountOfItems / 3) + 1;
    }

    @Transactional
    @Override
    public Integer countAmountOfPages(Integer numberOfItemsToDemonstrate) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(OrderItem.class);
        Integer amountOfItems = criteria.list().size();
        if (amountOfItems % numberOfItemsToDemonstrate == 0) {
            return amountOfItems % numberOfItemsToDemonstrate;
        } else
            return (amountOfItems % numberOfItemsToDemonstrate) + 1;
    }
}
