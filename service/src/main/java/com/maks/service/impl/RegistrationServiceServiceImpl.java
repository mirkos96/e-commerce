package com.maks.service.impl;

import com.maks.repository.IRegistrationServiceRepository;
import com.maks.repository.model.User;
import com.maks.repository.model.UserInfo;
import com.maks.service.IRegistrationServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceServiceImpl
        implements IRegistrationServiceService {

    private IRegistrationServiceRepository registrationServiceRepository;

    @Autowired
    public RegistrationServiceServiceImpl(IRegistrationServiceRepository registrationServiceRepository) {
        this.registrationServiceRepository = registrationServiceRepository;
    }

    @Override
    public void saveNewUser(User user) {
        registrationServiceRepository.saveNewUserIntoPersistentDataStorage(user);
    }
}
