package com.maks.service.impl;

import com.maks.repository.INewsServiceRepository;
import com.maks.service.INewsServiceService;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.service.modelDto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceServiceImpl implements INewsServiceService {

    private final IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities;
    private final INewsServiceRepository newsServiceRepository;

    @Autowired
    public NewsServiceServiceImpl(IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities, INewsServiceRepository newsServiceRepository) {
        this.convertDtoAndPersistentEntities = convertDtoAndPersistentEntities;
        this.newsServiceRepository = newsServiceRepository;
    }

    @Override
    public List<NewsDto> showNews() {
        return convertDtoAndPersistentEntities.convertNewsEntityToDto(
                newsServiceRepository.showNews());
    }

    @Override
    public void saveNewComment(Long newsId, String userLogin, String commentBody) {
        newsServiceRepository.saveNewComment(
                convertDtoAndPersistentEntities.convertToCommentEntity(
                        newsId, userLogin, commentBody));
    }

    @Override
    public void editNews(Long newsId, String newsHead, String newsBody) {
        newsServiceRepository.editNews(newsId, newsHead, newsBody);
    }

    @Override
    public void deleteComment(Long commentId) {
        newsServiceRepository.deleteComment(commentId);
    }

    @Override
    public void addNewPieceOfNews(String newsHead, String newsBody) {
        newsServiceRepository.addNewPieceOfNews(convertDtoAndPersistentEntities
                .setParametersToNewsEntity(newsHead, newsBody));
    }

    @Override
    public void deleteNews(Long newsId) {
        newsServiceRepository.deleteNews(newsId);
    }
}
