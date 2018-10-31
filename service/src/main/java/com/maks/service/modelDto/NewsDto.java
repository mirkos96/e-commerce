package com.maks.service.modelDto;

import java.util.List;

public class NewsDto {

    private Long newsId;
    private String newsHead;
    private String newsBody;
    private List<CommentDto> commentDtoList;

    public NewsDto() {
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getNewsHead() {
        return newsHead;
    }

    public void setNewsHead(String newsHead) {
        this.newsHead = newsHead;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    public List<CommentDto> getCommentDtoList() {
        return commentDtoList;
    }

    public void setCommentDtoList(List<CommentDto> commentDtoList) {
        this.commentDtoList = commentDtoList;
    }

    @Override
    public String toString() {
        return "NewsDto{" +
                "newsId=" + newsId +
                ", newsHead='" + newsHead + '\'' +
                ", newsBody='" + newsBody + '\'' +
                ", commentDtoList=" + commentDtoList +
                '}';
    }
}
