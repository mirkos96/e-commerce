package com.maks.repository.impl;

import com.maks.repository.IAccountServiceRepository;
import com.maks.repository.daoService.IAdditionalDatabaseOperationUser;
import com.maks.repository.daoService.IAdditionalDatabaseOperationVerification;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.Account;
import com.maks.repository.model.User;
import com.maks.repository.model.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AccountServiceRepositoryImpl implements IAccountServiceRepository {

    private final IAdditionalDatabaseOperationVerification<Verification, Long>
            verificationAdditionalDao;
    private final IAdditionalDatabaseOperationUser<User, Long> userAdditionalDao;
    private final ICrudOperation<User, Long> userDao;

    @Autowired
    public AccountServiceRepositoryImpl(@Qualifier("verificationDao")
                                                IAdditionalDatabaseOperationVerification<Verification, Long>
                                                verificationAdditionalDao,
                                        @Qualifier("userDao")
                                                IAdditionalDatabaseOperationUser<User, Long>
                                                userAdditionalDao,
                                        @Qualifier("userDao") ICrudOperation<User, Long>
                                                userDao) {
        this.verificationAdditionalDao = verificationAdditionalDao;
        this.userAdditionalDao = userAdditionalDao;
        this.userDao = userDao;
    }

    @Override
    public boolean checkIfUserWithSuchTokenExists(String verificationToken) {
        Verification verification =
                verificationAdditionalDao.getVerificationByToken(verificationToken);
        if (verification != null) {
            User user = getUserByToken(verification);
            Account account = setAccountActivated(user);
            user.setAccount(account);
            userDao.updateEntity(user);
            return true;
        }
        return false;
    }

    @Override
    public void blockUser(Long userId) {
        User user = userDao.readEntity(userId);
        Account account = user.getAccount();
        account.setBlocked(true);
        user.setAccount(account);
        userDao.updateEntity(user);
    }

    @Override
    public void unblockUser(Long userId) {
        User user = userDao.readEntity(userId);
        Account account = user.getAccount();
        account.setBlocked(false);
        userDao.updateEntity(user);
    }

    private User getUserByToken(Verification verification) {
        return userAdditionalDao.getByVerificationToken(verification);
    }

    private Account setAccountActivated(User user) {
        Account account = user.getAccount();
        account.setActivated(true);
        return account;
    }
}
