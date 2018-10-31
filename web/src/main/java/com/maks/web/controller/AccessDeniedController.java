package com.maks.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedController {

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView returnAccessDeniedPage() {
        return new ModelAndView("accessDenied");
    }
}
