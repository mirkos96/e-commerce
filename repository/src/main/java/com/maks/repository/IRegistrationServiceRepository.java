package com.maks.repository;

import com.maks.repository.model.User;

public interface IRegistrationServiceRepository {

    void saveNewUserIntoPersistentDataStorage(User user);
}
