package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationNews;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.News;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Qualifier("newsDao")
public class NewsDaoImpl implements ICrudOperation<News, Long>,
        IAdditionalDatabaseOperationNews<News, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public NewsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(News news) {
        getSession(sessionFactory).save(news);
    }

    @Transactional
    @Override
    public News readEntity(Long id) {
        return getSession(sessionFactory).get(News.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(News news) {
        getSession(sessionFactory).saveOrUpdate(news);
    }

    @Transactional
    @Override
    public void deleteEntity(News news) {
        getSession(sessionFactory).delete(news);
    }

    @Transactional
    @Override
    public List<News> getEntities() {
        Criteria criteria = getSession(sessionFactory).createCriteria(News.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<News>) criteria.list();
    }
}
