package com.maks.repository.impl;

import com.maks.repository.ILoginServiceRepository;
import com.maks.repository.daoService.IAdditionalDatabaseOperationUser;
import com.maks.repository.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoginServiceRepositoryImpl implements ILoginServiceRepository {

    public static final Logger log = Logger.getLogger(LoginServiceRepositoryImpl.class);

    private final IAdditionalDatabaseOperationUser userDao;

    @Autowired
    public LoginServiceRepositoryImpl(@Qualifier("userDao")
                                              IAdditionalDatabaseOperationUser userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User getUserForLogin(String username) {
        User user = (User) userDao.getByName(username);
        if (user == null) {
            log.info("No such user");
        } else {
            log.info(user.getPassword() + " " + user.getUserLogin() + " " + user.getUserRole());
        }
        return user;
    }
}
