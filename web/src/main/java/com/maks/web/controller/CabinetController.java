package com.maks.web.controller;

import com.maks.service.IPasswordServiceService;
import com.maks.service.IPersonalCabinetServiceService;
import com.maks.service.IUserServiceService;
import com.maks.service.modelDto.UserDto;
import com.maks.web.additionalService.IAdditionalSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CabinetController {

    private final IPasswordServiceService passwordServiceService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IAdditionalSessionService additionalSessionService;
    private final IPersonalCabinetServiceService personalCabinetServiceService;
    private final IUserServiceService userServiceService;

    @Autowired
    public CabinetController(IPasswordServiceService passwordServiceService,
                             BCryptPasswordEncoder passwordEncoder, IAdditionalSessionService additionalSessionService, IPersonalCabinetServiceService personalCabinetServiceService, IUserServiceService userServiceService) {
        this.passwordServiceService = passwordServiceService;
        this.passwordEncoder = passwordEncoder;
        this.additionalSessionService = additionalSessionService;
        this.personalCabinetServiceService = personalCabinetServiceService;
        this.userServiceService = userServiceService;
    }

    @RequestMapping(value = "/user/personal-cabinet", method = RequestMethod.GET)
    public ModelAndView showPersonalCabinet(HttpServletRequest request) {
        UserDto userDto =
                additionalSessionService.getUserFromSession(request);
        Long userId = userDto.getId();
        userDto = userServiceService.getUserById(userId);
        ModelAndView view = new ModelAndView("personal-cabinet");
        view.addObject("userDto", userDto);
        view.addObject("user", new UserDto());
        return view;
    }

    @RequestMapping(value = "/user/personal-cabinet/change-address",
            method = RequestMethod.POST)
    public String changeAddress(HttpServletRequest request) {
        String address = request.getParameter("address");
        Long userId = Long.valueOf(request.getParameter("userId"));
        if (personalCabinetServiceService.setNewAddress(address, userId)) {
            return "redirect:/user/personal-cabinet?change=addressTrue";
        }
        return "redirect:/user/personal-cabinet?change=addressFalse";
    }

    @RequestMapping(value = "/user/personal-cabinet/change-phone", method = RequestMethod.POST)
    public String changePhone(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        Long userId = Long.valueOf(request.getParameter("userId"));
        if (personalCabinetServiceService.setNewPhone(phone, userId)) {
            return "redirect:/user/personal-cabinet?change=phoneSuccess";
        }
        return "redirect:/user/personal-cabinet?change=phoneFailure";
    }

    @RequestMapping(value = "/user/personal-cabinet/change-name-surname",
            method = RequestMethod.POST)
    public String changeNameSurname(HttpServletRequest request) {
        String name = request.getParameter("name");
        Long userId = Long.valueOf(request.getParameter("userId"));
        if (personalCabinetServiceService.setNewNameAndSurname(name, userId)) {
            return "redirect:/user/personal-cabinet?change=nameSuccess";
        }
        return "redirect:/user/personal-cabinet?change=nameFailure";
    }

    @RequestMapping(value = "/user/personal-cabinet/change-password",
            method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        newPassword = cryptPassword(newPassword);
        Long userId = Long.parseLong(request.getParameter("userId"));
        if (passwordServiceService.changePassword(userId, oldPassword, newPassword,
                passwordEncoder)) {
            return "redirect:/user/personal-cabinet?change=passSuccess";
        }
        return "redirect:/user/personal-cabinet?change=passFailure";
    }

    private String cryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
