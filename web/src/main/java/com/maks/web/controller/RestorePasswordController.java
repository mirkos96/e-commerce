package com.maks.web.controller;

import com.maks.service.IPasswordServiceService;
import com.maks.web.additionalService.IAdditionalMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class RestorePasswordController {

    private final IPasswordServiceService passwordServiceService;
    private final IAdditionalMailService mailService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RestorePasswordController(IPasswordServiceService passwordServiceService,
                                     IAdditionalMailService mailService,
                                     BCryptPasswordEncoder passwordEncoder) {
        this.passwordServiceService = passwordServiceService;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/restore-password", method = RequestMethod.GET)
    public ModelAndView showRestorePage() {
        return new ModelAndView("restorePassword");
    }

    @RequestMapping(value = "/restore-password/send-new", method = RequestMethod.POST)
    public String resetPassword(@RequestParam("email") String email) {
        String temporaryPass = prepareTempPassword();
        String bcryptedPass = prepareBcryptedPass(passwordEncoder, temporaryPass);
        if (passwordServiceService.resetPassword(email, bcryptedPass)) {
            mailService.sendResetEmail(email, temporaryPass);
            return "redirect:/login?reset=true";
        }
        return "redirect:/login?reset=false";
    }

    private String prepareTempPassword() {
        Random rd = new Random();
        return String.valueOf(rd.nextInt(123456));
    }

    private String prepareBcryptedPass(BCryptPasswordEncoder passwordEncoder,
                                       String tempPass) {
        return passwordEncoder.encode(tempPass);
    }
}
