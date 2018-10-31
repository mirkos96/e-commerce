package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_user_message_reply")
public class ReplyOnUserMessage implements Serializable {

    public static final Long serialVersionUID = 4546436L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_reply_id")
    private Long replyId;

    @Column(name = "f_reply_body")
    private String replyBody;

    @OneToOne
    @JoinColumn(name = "f_user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "f_user_message_id")
    private MessageFromUser messageFromUser;

    public ReplyOnUserMessage() {
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getReplyBody() {
        return replyBody;
    }

    public void setReplyBody(String replyBody) {
        this.replyBody = replyBody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MessageFromUser getMessageFromUser() {
        return messageFromUser;
    }

    public void setMessageFromUser(MessageFromUser messageFromUser) {
        this.messageFromUser = messageFromUser;
    }

    @Override
    public String toString() {
        return "ReplyOnUserMessage{" +
                "replyId=" + replyId +
                ", replyBody='" + replyBody + '\'' +
                ", user=" + user +
                ", messageFromUser=" + messageFromUser +
                '}';
    }
}
