package com.maks.web.controller;

import com.maks.repository.model.User;
import com.maks.service.IRegistrationServiceService;
import com.maks.service.modelDto.UserDto;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.web.additionalService.IAdditionalMailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    public static final Logger log = Logger.getLogger(RegistrationController.class);
    private BCryptPasswordEncoder encoder;
    private IConvertDtoAndPersistentEntities convertDtoInPersistentEntity;
    private IRegistrationServiceService registrationServiceService;
    private final IAdditionalMailService mailService;

    @Autowired
    public RegistrationController(BCryptPasswordEncoder encoder,
                                  IConvertDtoAndPersistentEntities
                                          convertDtoInPersistentEntity,
                                  IRegistrationServiceService
                                          registrationServiceService,
                                  IAdditionalMailService mailService) {
        this.encoder = encoder;
        this.convertDtoInPersistentEntity = convertDtoInPersistentEntity;
        this.registrationServiceService = registrationServiceService;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage() {
        return new ModelAndView("registration", "userDto", new UserDto());
    }

    @RequestMapping(value = "/registration/add-new-user", method = RequestMethod.POST)
    public String registerNewUser(@Valid @ModelAttribute("userDto") UserDto userDto,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        userDto.setPassword(encodePassword(userDto.getPassword()));
        User user = convertDtoInPersistentEntity.convertUserDtoToEntity(userDto);
        log.info(user.getUserLogin() + " " + user.getPassword());
        registrationServiceService.saveNewUser(user);
        mailService.sendRegistrationEmail(userDto.getUserLogin());
        return "redirect:/login?action=addingSuccess";
    }

    private String encodePassword(String password) {
        return encoder.encode(password);
    }
}
