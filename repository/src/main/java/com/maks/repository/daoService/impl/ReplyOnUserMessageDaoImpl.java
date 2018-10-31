package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.ReplyOnUserMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Qualifier("replyDao")
public class ReplyOnUserMessageDaoImpl implements ICrudOperation<ReplyOnUserMessage, Long>{

    private final SessionFactory sessionFactory;

    @Autowired
    public ReplyOnUserMessageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(ReplyOnUserMessage replyOnUserMessage) {
        getSession(sessionFactory).save(replyOnUserMessage);
    }

    @Transactional
    @Override
    public ReplyOnUserMessage readEntity(Long id) {
        return getSession(sessionFactory).get(ReplyOnUserMessage.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(ReplyOnUserMessage replyOnUserMessage) {
        getSession(sessionFactory).saveOrUpdate(replyOnUserMessage);
    }

    @Transactional
    @Override
    public void deleteEntity(ReplyOnUserMessage replyOnUserMessage) {
        getSession(sessionFactory).delete(replyOnUserMessage);
    }
}
