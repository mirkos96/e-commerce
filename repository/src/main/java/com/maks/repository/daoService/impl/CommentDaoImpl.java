package com.maks.repository.daoService.impl;

import com.maks.repository.daoService.IAdditionalDatabaseOperationComment;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Qualifier("commentDao")
public class CommentDaoImpl implements ICrudOperation<Comment, Long>,
        IAdditionalDatabaseOperationComment<Comment, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createEntity(Comment comment) {
        getSession(sessionFactory).save(comment);
    }

    @Transactional
    @Override
    public Comment readEntity(Long commentId) {
        return getSession(sessionFactory).get(Comment.class, commentId);
    }

    @Transactional
    @Override
    public void updateEntity(Comment comment) {
        getSession(sessionFactory).saveOrUpdate(comment);
    }

    @Transactional
    @Override
    public void deleteEntity(Comment comment) {
        getSession(sessionFactory).delete(comment);
    }

    @Transactional
    @Override
    public void deleteCommentById(Long id) {
        Session session = getSession(sessionFactory);
        String hql = "delete from Comment where commentId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
