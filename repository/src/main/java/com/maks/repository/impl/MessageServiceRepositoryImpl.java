package com.maks.repository.impl;

import com.maks.repository.IMessageServiceRepository;
import com.maks.repository.daoService.IAdditionalDatabaseOperationMessageFUser;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.MessageFromUser;
import com.maks.repository.model.ReplyOnUserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageServiceRepositoryImpl implements IMessageServiceRepository {

    private final IAdditionalDatabaseOperationMessageFUser<MessageFromUser, Long>
            messageAdditionalDao;
    private final ICrudOperation<ReplyOnUserMessage, Long> replyDao;

    @Autowired
    public MessageServiceRepositoryImpl(@Qualifier("messageFromUserDao")
                                                IAdditionalDatabaseOperationMessageFUser<MessageFromUser, Long>
                                                messageAdditionalDao,
                                        @Qualifier("replyDao")
                                                ICrudOperation<ReplyOnUserMessage, Long>
                                                replyDao) {
        this.messageAdditionalDao = messageAdditionalDao;
        this.replyDao = replyDao;
    }

    @Override
    public List<MessageFromUser> getMessages() {
        return messageAdditionalDao.getEntities();
    }

    @Override
    public void saveAdminReply(ReplyOnUserMessage reply) {
        replyDao.createEntity(reply);
    }
}
