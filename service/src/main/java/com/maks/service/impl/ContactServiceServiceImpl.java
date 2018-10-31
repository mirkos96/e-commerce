package com.maks.service.impl;

import com.maks.repository.IContactServiceRepository;
import com.maks.repository.model.MessageFromUser;
import com.maks.service.IContactServiceService;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.service.modelDto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceServiceImpl implements IContactServiceService {

    private final IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities;
    private final IContactServiceRepository contactServiceRepository;

    @Autowired
    public ContactServiceServiceImpl(IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities, IContactServiceRepository contactServiceRepository) {
        this.convertDtoAndPersistentEntities = convertDtoAndPersistentEntities;
        this.contactServiceRepository = contactServiceRepository;
    }

    @Override
    public void saveNewMessageFromUser(MessageDto messageDtoFromUser) {
        MessageFromUser messageFromUser = convertDtoAndPersistentEntities
                .convertMessageDtoIntoPersistentObject(messageDtoFromUser);
        contactServiceRepository.saveNewMessageFromUser(messageFromUser);
    }
}
