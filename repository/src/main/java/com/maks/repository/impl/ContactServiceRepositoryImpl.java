package com.maks.repository.impl;

import com.maks.repository.IContactServiceRepository;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.MessageFromUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ContactServiceRepositoryImpl implements IContactServiceRepository {

    private final ICrudOperation<MessageFromUser, Long> messageDao;

    @Autowired
    public ContactServiceRepositoryImpl(
            @Qualifier("messageFromUserDao")
                    ICrudOperation<MessageFromUser, Long> messageDao) {
        this.messageDao = messageDao;
    }

    @Transactional
    @Override
    public void saveNewMessageFromUser(MessageFromUser messageFromUser) {
        messageDao.createEntity(messageFromUser);
    }
}
