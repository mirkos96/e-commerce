package com.maks.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogInController {

    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String showLogInPage() {
        return "login";
    }

}
