package com.maks.web.controller;

import com.maks.service.INewsServiceService;
import com.maks.service.modelDto.NewsDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminNewsController {

    private final INewsServiceService newsServiceService;
    public static final Logger log = Logger.getLogger(AdminNewsController.class);

    @Autowired
    public AdminNewsController(INewsServiceService newsServiceService) {
        this.newsServiceService = newsServiceService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/news-comment-control", method = RequestMethod.GET)
    public ModelAndView showControlOverNewsAndComments() {
        ModelAndView view = new ModelAndView("newsControl");
        List<NewsDto> newsDtoList = newsServiceService.showNews();
        view.addObject("newsDto", newsDtoList);
        return view;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/news-comment-control/edit", method = RequestMethod.POST)
    public String editNews(HttpServletRequest request) {
        String newsHead = request.getParameter("newsHead");
        String newsBody = request.getParameter("newsBody");
        Long newsId = Long.parseLong(request.getParameter("newsId"));
        newsServiceService.editNews(newsId, newsHead, newsBody);
        return "redirect:/admin/news-comment-control?action=editSuccess";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/news-comment-control/delete-comment", method = RequestMethod.POST)
    public String deleteComment(HttpServletRequest request) {
        Long commentId = Long.valueOf(request.getParameter("commentId"));
        newsServiceService.deleteComment(commentId);
        return "redirect:/admin/news-comment-control?action=deleteCommentSuccess";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/news-comment-control/add-news", method = RequestMethod.POST)
    public String addNewPieceOfNews(HttpServletRequest request) {
        String newsHead = request.getParameter("newsHead");
        String newsBody = request.getParameter("newsBody");
        log.info(newsHead);
        log.info(newsBody);
        newsServiceService.addNewPieceOfNews(newsHead, newsBody);
        return "redirect:/admin/news-comment-control?action=newsSuccess";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/news-comment-control/delete-news", method = RequestMethod.POST)
    public String deleteNews(HttpServletRequest request) {
        Long newsId = Long.valueOf(request.getParameter("newsItemId"));
        log.info("THIS IS NEWS ID!!!" + newsId);
        newsServiceService.deleteNews(newsId);
        return "redirect:/admin/news-comment-control";
    }
}
