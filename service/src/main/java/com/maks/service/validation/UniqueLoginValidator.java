package com.maks.service.validation;

import com.maks.service.validation.annotation.UniqueLogin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private final SessionFactory sessionFactory;

    @Autowired
    public UniqueLoginValidator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        Session session = sessionFactory.openSession();
        String hql = "select userLogin from User where userLogin = :login";
        Query query = session.createQuery(hql);
        query.setParameter("login", login);
        String log = (String) query.uniqueResult();
        session.close();
        return log == null;
    }
}
