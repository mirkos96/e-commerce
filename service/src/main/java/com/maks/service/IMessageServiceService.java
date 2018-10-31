package com.maks.service;

import com.maks.service.modelDto.MessageDto;
import com.maks.service.modelDto.ReplyOnUserMessageDto;

import java.util.List;

public interface IMessageServiceService {

    List<MessageDto> getMessages();

    void saveAdminReply(ReplyOnUserMessageDto replyDto);
}
