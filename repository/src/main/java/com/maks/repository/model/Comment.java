package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_comment")
public class Comment implements Serializable{

    public static final Long serialVersionUID = 4325657L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_comment_id")
    private Long commentId;

    @Column(name = "f_comment")
    private String commentBody;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "f_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "f_news_id")
    private News news;

    public Comment() {
    }

    public Comment(String commentBody, User user, News news) {
        this.commentBody = commentBody;
        this.user = user;
        this.news = news;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentBody='" + commentBody + '\'' +
                ", user=" + user +
                ", news=" + news +
                '}';
    }
}
