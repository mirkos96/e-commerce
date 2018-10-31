package com.maks.service.impl;

import com.maks.repository.IPersonalCabinetServiceRepository;
import com.maks.service.IPersonalCabinetServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalCabinetServiceServiceImpl implements IPersonalCabinetServiceService {

    private final IPersonalCabinetServiceRepository personalCabinetServiceRepository;

    @Autowired
    public PersonalCabinetServiceServiceImpl(IPersonalCabinetServiceRepository personalCabinetServiceRepository) {
        this.personalCabinetServiceRepository = personalCabinetServiceRepository;
    }

    @Override
    public boolean setNewNameAndSurname(String name, Long userId) {
        return personalCabinetServiceRepository.setNewNameSurname(name, userId);
    }

    @Override
    public boolean setNewAddress(String address, Long userId) {
        return personalCabinetServiceRepository.setNewAddress(address, userId);
    }

    @Override
    public boolean setNewPhone(String phone, Long userId) {
        return personalCabinetServiceRepository.setNewPhone(phone, userId);
    }
}
