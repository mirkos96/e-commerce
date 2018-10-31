package com.maks.web.controller;

import com.maks.service.INewsServiceService;
import com.maks.service.modelDto.NewsDto;
import com.maks.web.additionalService.IAdditionalSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class NewsController {

    private final INewsServiceService newsServiceService;
    private final IAdditionalSessionService additionalSessionService;

    @Autowired
    public NewsController(INewsServiceService newsServiceService, IAdditionalSessionService additionalSessionService) {
        this.newsServiceService = newsServiceService;

        this.additionalSessionService = additionalSessionService;
    }

    @RequestMapping(value = "/user/news", method = RequestMethod.GET)
    public ModelAndView showNewsPage(HttpServletRequest request) {
        List<NewsDto> newsDtoList = newsServiceService.showNews();
        ModelAndView view = new ModelAndView("news");
        view.addObject("userDto",
                additionalSessionService.getUserFromSession(request));
        view.addObject("newsAndCommentList", newsDtoList);
        return view;
    }

    @RequestMapping(value = "/user/news/add-comment", method = RequestMethod.POST)
    public String addComment(HttpServletRequest request) {
        Long newsId = Long.valueOf(request.getParameter("newsId"));
        String userLogin = request.getParameter("userLogin");
        String commentBody = request.getParameter("commentBody");
        newsServiceService.saveNewComment(newsId, userLogin, commentBody);
        return "redirect:/user/news";
    }
}
