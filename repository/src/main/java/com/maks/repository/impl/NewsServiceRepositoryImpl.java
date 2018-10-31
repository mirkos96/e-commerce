package com.maks.repository.impl;

import com.maks.repository.INewsServiceRepository;
import com.maks.repository.daoService.IAdditionalDatabaseOperationComment;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.daoService.IAdditionalDatabaseOperationNews;
import com.maks.repository.model.Comment;
import com.maks.repository.model.News;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class NewsServiceRepositoryImpl implements INewsServiceRepository {

    public static final Logger log = Logger.getLogger(NewsServiceRepositoryImpl.class);
    private final ICrudOperation<Comment, Long> commentDao;
    private final IAdditionalDatabaseOperationComment<Comment, Long> commentAdditionalDao;
    private final ICrudOperation<News, Long> newsDao;
    private final IAdditionalDatabaseOperationNews<News, Long> newsAdditionalDao;

    @Autowired
    public NewsServiceRepositoryImpl(
            @Qualifier("commentDao")
                    ICrudOperation<Comment, Long> commentDao,
            @Qualifier("commentDao")
                    IAdditionalDatabaseOperationComment<Comment, Long> commentAdditionalDao,
            @Qualifier("newsDao")
                    ICrudOperation<News, Long> newsDao,
            @Qualifier("newsDao")
                    IAdditionalDatabaseOperationNews<News, Long> newsAdditionalDao) {
        this.commentDao = commentDao;
        this.commentAdditionalDao = commentAdditionalDao;
        this.newsDao = newsDao;
        this.newsAdditionalDao = newsAdditionalDao;
    }

    @Transactional
    @Override
    public List<News> showNews() {
        return newsAdditionalDao.getEntities();
    }

    @Transactional
    @Override
    public void saveNewComment(Comment comment) {
        commentDao.createEntity(comment);
    }

    @Transactional
    @Override
    public void editNews(Long newsId, String newsHead, String newsBody) {
        News news = newsDao.readEntity(newsId);
        news.setNewsHead(newsHead);
        news.setNewsBody(newsBody);
        newsDao.updateEntity(news);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        commentAdditionalDao.deleteCommentById(commentId);
    }

    @Transactional
    @Override
    public void addNewPieceOfNews(News news) {
        newsDao.createEntity(news);
    }

    @Transactional
    @Override
    public void deleteNews(Long newsId) {
        News news = newsDao.readEntity(newsId);
        newsDao.deleteEntity(news);
    }
}
