package com.maks.repository.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_news")
public class News implements Serializable {

    public static final Long serialVersionUID = 4256457574643L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_news_id")
    private Long newsId;

    @Column(name = "f_news_head")
    private String newsHead;

    @Column(name = "f_news_body")
    private String newsBody;

    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "news")
    private List<Comment> commentList;

    public News() {
    }

    public News(String newsHead, String newsBody, List<Comment> commentList) {
        this.newsHead = newsHead;
        this.newsBody = newsBody;
        this.commentList = commentList;
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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsHead='" + newsHead + '\'' +
                ", newsBody='" + newsBody + '\'' +
                ", commentList=" + commentList +
                '}';
    }
}
