package com.maks.repository;

import com.maks.repository.model.User;

public interface ILoginServiceRepository {

    User getUserForLogin(String username);
}
