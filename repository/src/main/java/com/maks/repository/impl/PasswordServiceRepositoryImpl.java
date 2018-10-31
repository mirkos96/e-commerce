package com.maks.repository.impl;

import com.maks.repository.IPasswordServiceRepository;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PasswordServiceRepositoryImpl implements IPasswordServiceRepository {

    public static final Logger log = Logger.getLogger(PasswordServiceRepositoryImpl.class);
    private final ICrudOperation<User, Long> userDao;
    ;

    @Autowired
    public PasswordServiceRepositoryImpl(@Qualifier("userDao")
                                                 ICrudOperation<User, Long> userDao) {
        this.userDao = userDao;
    }


    @Transactional
    @Override
    public boolean getPasswordFromDatabaseAndCheckEquality(Long userId, String oldPassword,
                                                           BCryptPasswordEncoder passwordEncoder) {
        User user = userDao.readEntity(userId);
        if (user != null && checkPasswordEquality(user, oldPassword, passwordEncoder)) {
            log.info("Found needed user with login: " + user.getUserLogin());
            log.info("And passwords are equal");
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void setNewPassword(Long userId, String newPassword) {
        User user = userDao.readEntity(userId);
        user.setPassword(newPassword);
        userDao.updateEntity(user);
        log.info("User: " + user.getUserLogin() + " changed password");
    }

    @Override
    public void resetPassword(User user, String bcryptedPass) {
        user.setPassword(bcryptedPass);
        userDao.updateEntity(user);
    }

    private boolean checkPasswordEquality(User user, String oldPassword,
                                          BCryptPasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            log.info("passwords match");
            return true;
        } else
            log.info("passwords do not match");
        return false;
    }
}