package com.maks.repository;

import com.maks.repository.model.MessageFromUser;
import com.maks.repository.model.ReplyOnUserMessage;

import java.util.List;

public interface IMessageServiceRepository {

    List<MessageFromUser> getMessages();

    void saveAdminReply(ReplyOnUserMessage reply);
}
