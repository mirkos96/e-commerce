package com.maks.service;

import com.maks.service.modelDto.MessageDto;

public interface IContactServiceService {

    void saveNewMessageFromUser(MessageDto messageDtoFromUser);
}
