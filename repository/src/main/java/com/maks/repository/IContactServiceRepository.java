package com.maks.repository;

import com.maks.repository.model.MessageFromUser;

public interface IContactServiceRepository {

    void saveNewMessageFromUser(MessageFromUser messageFromUser);
}
