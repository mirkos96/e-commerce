package com.maks.service.impl;

import com.maks.repository.IMessageServiceRepository;
import com.maks.service.IMessageServiceService;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.service.modelDto.MessageDto;
import com.maks.service.modelDto.ReplyOnUserMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceService implements IMessageServiceService {

    private final IMessageServiceRepository messageServiceRepository;
    private final IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities;

    @Autowired
    public MessageServiceService(IMessageServiceRepository messageServiceRepository,
                                 IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities) {
        this.messageServiceRepository = messageServiceRepository;
        this.convertDtoAndPersistentEntities = convertDtoAndPersistentEntities;
    }

    @Override
    public List<MessageDto> getMessages() {
        return convertDtoAndPersistentEntities.convertMessageEntitiesToDto(
                messageServiceRepository.getMessages());
    }

    @Override
    public void saveAdminReply(ReplyOnUserMessageDto replyDto) {
        messageServiceRepository.saveAdminReply(convertDtoAndPersistentEntities
                .convertDtoToEntityReply(replyDto));
    }
}
