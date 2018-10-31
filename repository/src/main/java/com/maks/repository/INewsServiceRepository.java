package com.maks.repository;

import com.maks.repository.model.Comment;
import com.maks.repository.model.News;

import java.util.List;

public interface INewsServiceRepository {

    List<News> showNews();

    void saveNewComment(Comment comment);

    void editNews(Long userId, String newsHead, String newsBody);

    void deleteComment(Long commentId);

    void addNewPieceOfNews(News news);

    void deleteNews(Long newsId);
}
