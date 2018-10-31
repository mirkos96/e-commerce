package com.maks.web.controller;

import com.maks.service.modelDto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminPageController {

    @RequestMapping(value = "/admin/admin-page", method = RequestMethod.GET)
    public ModelAndView showAdminPage(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("admin");
        view.addObject("userDto", getUserFromSession(request));
        return view;
    }

    private UserDto getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UserDto) session.getAttribute("userDto");
    }
}
