package com.maks.service.modelDto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ReplyOnUserMessageDto {

    private Long replyId;
    @NotNull
    @Length(max = 255, min = 20)
    private String replyBody;
    private Long userId;
    private Long userMessageId;
    private String userLogin;

    public ReplyOnUserMessageDto() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(Long userMessageId) {
        this.userMessageId = userMessageId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "ReplyOnUserMessageDto{" +
                "replyId=" + replyId +
                ", replyBody='" + replyBody + '\'' +
                ", userId=" + userId +
                ", userMessageId=" + userMessageId +
                ",userLogin=" + userLogin +
                '}';
    }
}
