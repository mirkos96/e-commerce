package com.maks.service.modelDto;

import com.maks.repository.model.User;

public class CommentDto {

    private Long commentId;
    private String commentBody;
    private UserDto userDto;
    private Long newsId;

    public CommentDto() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "commentId=" + commentId +
                ", commentBody='" + commentBody + '\'' +
                ", userDto=" + userDto +
                ", newsId=" + newsId +
                '}';
    }
}
