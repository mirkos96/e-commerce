package com.maks.web.controller;

import com.maks.service.IContactServiceService;
import com.maks.service.modelDto.MessageDto;
import com.maks.web.additionalService.IAdditionalSessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ContactsController {

    private static final Logger log = Logger.getLogger(ContactsController.class);

    private final IContactServiceService contactServiceService;
    private final IAdditionalSessionService additionalSessionService;

    @Autowired
    public ContactsController(IContactServiceService contactServiceService, IAdditionalSessionService additionalSessionService) {
        this.contactServiceService = contactServiceService;
        this.additionalSessionService = additionalSessionService;
    }

    @RequestMapping(value = "/user/contacts", method = RequestMethod.GET)
    public ModelAndView showContactPage(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("contacts",
                "messageDto", new MessageDto());
        view.addObject("userDto",
                additionalSessionService.getUserFromSession(request));
        return view;
    }

    @RequestMapping(value = "/user/contacts/send-message", method = RequestMethod.POST)
    private String sendMessageFromUser(@Valid @ModelAttribute("messageDto") MessageDto messageDto,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/user/contacts?action=sentFailure";
        }
        log.info(messageDto);
        contactServiceService.saveNewMessageFromUser(messageDto);
        return "redirect:/user/contacts?action=sentSuccess";
    }
}
