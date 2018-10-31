package com.maks.service.impl;

import com.maks.repository.IPersonalCabinetServiceRepository;
import com.maks.repository.model.User;
import com.maks.repository.model.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonalCabinetServiceRepositoryImpl implements IPersonalCabinetServiceRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonalCabinetServiceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public boolean setNewNameSurname(String name, Long userId) {
        User user = getUserById(userId);
        if (user != null) {
            Session session = sessionFactory.getCurrentSession();
            UserInfo userInfo = user.getUserInfo();
            userInfo.setNameSurname(name);
            user.setUserInfo(userInfo);
            session.saveOrUpdate(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean setNewAddress(String address, Long userId) {
        User user = getUserById(userId);
        if (user != null) {
            Session session = sessionFactory.getCurrentSession();
            UserInfo userInfo = user.getUserInfo();
            userInfo.setUserAddress(address);
            user.setUserInfo(userInfo);
            session.saveOrUpdate(user);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean setNewPhone(String phone, Long userId) {
        User user = getUserById(userId);
        if (user != null) {
            Session session = sessionFactory.getCurrentSession();
            UserInfo userInfo = user.getUserInfo();
            userInfo.setUserPhone(phone);
            session.saveOrUpdate(user);
            return true;
        }
        return false;
    }

    @Transactional
    public User getUserById(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("userId", userId));
        return (User) criteria.uniqueResult();
    }
}
