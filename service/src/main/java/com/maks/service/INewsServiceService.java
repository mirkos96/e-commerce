package com.maks.service;

import com.maks.service.modelDto.CommentDto;
import com.maks.service.modelDto.NewsDto;

import java.util.List;

public interface INewsServiceService {

    List<NewsDto> showNews();

    void saveNewComment(Long newsId, String userLogin, String commentBody);

    void editNews(Long newsId, String newsHead, String newsBody);

    void deleteComment(Long commentId);

    void addNewPieceOfNews(String newsHead, String newsBody);

    void deleteNews(Long newsId);
}
