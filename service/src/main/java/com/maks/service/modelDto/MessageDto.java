package com.maks.service.modelDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageDto {

    private Long messageId;
    private Long userId;
    @NotNull
    private String userLogin;
    private String messageTheme;
    @NotNull
    @Size(min = 1, max = 250)
    private String messageBody;
    private String receivingDate;

    public MessageDto() {
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                ", messageTheme='" + messageTheme + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", receivingDate='" + receivingDate + '\'' +
                '}';
    }
}
