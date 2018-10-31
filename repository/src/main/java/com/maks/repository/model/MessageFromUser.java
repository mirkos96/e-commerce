package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "t_message_from_user")
public class MessageFromUser implements Serializable {

    public static final Long serialVersionUID = 423565765L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_message_id")
    private Long messageId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "f_user_id")
    private User user;

    @Column(name = "f_message_theme")
    private String messageTheme;

    @Column(name = "f_message_body")
    private String messageBody;

    @Column(name = "f_receiving_date")
    private String receivingDate;

    public MessageFromUser() {
    }

    public MessageFromUser(User user, String messageTheme,
                           String messageBody, String receivingDate) {
        this.user = user;
        this.messageTheme = messageTheme;
        this.messageBody = messageBody;
        this.receivingDate = receivingDate;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessageTheme() {
        return messageTheme;
    }

    public void setMessageTheme(String messageTheme) {
        this.messageTheme = messageTheme;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(String receivingDate) {
        this.receivingDate = receivingDate;
    }

    @Override
    public String toString() {
        return "MessageFromUser{" +
                "messageId=" + messageId +
                ", user=" + user +
                ", messageTheme='" + messageTheme + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", receivingDate='" + receivingDate + '\'' +
                '}';
    }
}
