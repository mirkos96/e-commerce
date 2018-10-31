package com.maks.service;

import com.maks.repository.model.User;

public interface ILoginServiceService {

    User getUserForLogin(String username);
}
