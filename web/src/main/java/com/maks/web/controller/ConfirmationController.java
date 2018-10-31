package com.maks.web.controller;

import com.maks.service.IAccountServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmationController {

    private final IAccountServiceService accountServiceService;

    @Autowired
    public ConfirmationController(IAccountServiceService accountServiceService) {
        this.accountServiceService = accountServiceService;
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public String confirmationPage(@RequestParam("confirm") String verificationToken) {
        if (verificationToken == null) {
            return "redirect:/login?account=null";
        }
        if (accountServiceService.checkIfUserWithSuchTokenExists(verificationToken)) {
            return "redirect:/login?account=confirmed";
        }
        return "redirect:/login?account=notConfirmed";
    }
}
