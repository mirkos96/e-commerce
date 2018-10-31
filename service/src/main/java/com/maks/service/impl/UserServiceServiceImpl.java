package com.maks.service.impl;

import com.maks.repository.IUserServiceRepository;
import com.maks.service.IUserServiceService;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.service.converter.impl.ConvertDtoAndPersistentEntityImpl;
import com.maks.service.modelDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceServiceImpl implements IUserServiceService {

    private final IUserServiceRepository userServiceRepository;
    private final IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities;

    @Autowired
    public UserServiceServiceImpl(IUserServiceRepository userServiceRepository, IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities) {
        this.userServiceRepository = userServiceRepository;
        this.convertDtoAndPersistentEntities = convertDtoAndPersistentEntities;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return convertDtoAndPersistentEntities.convertEntityToDtoUser(
                userServiceRepository.getAllUsers());
    }

    @Override
    public void deleteUserById(Long userId) {
        userServiceRepository.deleteUserById(userId);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return convertDtoAndPersistentEntities.convertEntityToDtoUser(
                userServiceRepository.getUserById(userId));
    }
}
