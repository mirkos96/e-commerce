package com.maks.repository.impl;

import com.maks.repository.IRegistrationServiceRepository;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegistrationServiceRepositoryImpl
        implements IRegistrationServiceRepository {

    private final ICrudOperation<User, Long> userDao;

    @Autowired
    public RegistrationServiceRepositoryImpl(@Qualifier("userDao")
                                                     ICrudOperation<User, Long> userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void saveNewUserIntoPersistentDataStorage(User user) {
        userDao.createEntity(user);
    }
}
