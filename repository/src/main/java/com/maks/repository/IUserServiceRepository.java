package com.maks.repository;

import com.maks.repository.model.User;

import java.util.List;

public interface IUserServiceRepository {

    List<User> getAllUsers();

    void deleteUserById(Long userId);

    User getUserById(Long userId);
}
