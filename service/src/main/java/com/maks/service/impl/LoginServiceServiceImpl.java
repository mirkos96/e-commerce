package com.maks.service.impl;

import com.maks.repository.ILoginServiceRepository;
import com.maks.repository.model.User;
import com.maks.service.ILoginServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceServiceImpl implements ILoginServiceService{

    private final ILoginServiceRepository loginServiceRepository;

    @Autowired
    public LoginServiceServiceImpl(ILoginServiceRepository loginServiceRepository) {
        this.loginServiceRepository = loginServiceRepository;
    }


    @Override
    public User getUserForLogin(String username) {
        return loginServiceRepository.getUserForLogin(username);
    }
}
