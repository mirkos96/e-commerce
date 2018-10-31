package com.maks.repository.impl;

import com.maks.repository.IUserServiceRepository;
import com.maks.repository.daoService.IAdditionalDatabaseOperationUser;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserServiceRepositoryImpl implements IUserServiceRepository {

    private final IAdditionalDatabaseOperationUser<User, Long> userAdditionalDao;
    private final ICrudOperation<User, Long> userDao;

    @Autowired
    public UserServiceRepositoryImpl(@Qualifier("userDao")
                                             IAdditionalDatabaseOperationUser<User, Long>
                                             userAdditionalDao,
                                     @Qualifier("userDao")
                                             ICrudOperation<User, Long> userDao) {
        this.userAdditionalDao = userAdditionalDao;
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userAdditionalDao.getListOfEntities();
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        userAdditionalDao.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.readEntity(userId);
    }
}
