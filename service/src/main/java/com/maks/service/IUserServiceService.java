package com.maks.service;

import com.maks.service.modelDto.UserDto;

import java.util.List;

public interface IUserServiceService {

    List<UserDto> getAllUsers();

    void deleteUserById(Long userId);

    UserDto getUserById(Long userId);
}
