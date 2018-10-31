package com.maks.service.impl;

import com.maks.repository.IAccountServiceRepository;
import com.maks.service.IAccountServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceServiceImpl implements IAccountServiceService {

    private final IAccountServiceRepository accountServiceRepository;

    @Autowired
    public AccountServiceServiceImpl(IAccountServiceRepository accountServiceRepository) {
        this.accountServiceRepository = accountServiceRepository;
    }

    @Override
    public boolean checkIfUserWithSuchTokenExists(String verificationToken) {
        return accountServiceRepository.checkIfUserWithSuchTokenExists(verificationToken);
    }

    @Override
    public void blockUser(Long userId) {
        accountServiceRepository.blockUser(userId);
    }

    @Override
    public void unblockUser(Long userId) {
        accountServiceRepository.unblockUser(userId);
    }
}
