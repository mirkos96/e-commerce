package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationMessageFUser;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.MessageFromUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Qualifier("messageFromUserDao")
public class MessageFromUserDaoImpl implements ICrudOperation<MessageFromUser, Long>,
        IAdditionalDatabaseOperationMessageFUser<MessageFromUser, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public MessageFromUserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(MessageFromUser messageFromUser) {
        getSession(sessionFactory).save(messageFromUser);
    }

    @Transactional
    @Override
    public MessageFromUser readEntity(Long id) {
        return getSession(sessionFactory).get(MessageFromUser.class, id);
    }

    @Transactional
    @Override
    public void updateEntity(MessageFromUser messageFromUser) {
        getSession(sessionFactory).saveOrUpdate(messageFromUser);
    }

    @Transactional
    @Override
    public void deleteEntity(MessageFromUser messageFromUser) {
        getSession(sessionFactory).delete(messageFromUser);
    }

    @Transactional
    @Override
    public List<MessageFromUser> getEntities() {
        Criteria criteria = getSession(sessionFactory).createCriteria(MessageFromUser.class);
        return (List<MessageFromUser>) criteria.list();
    }
}
