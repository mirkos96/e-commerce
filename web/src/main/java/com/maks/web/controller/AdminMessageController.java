package com.maks.web.controller;

import com.maks.service.IMessageServiceService;
import com.maks.service.modelDto.ReplyOnUserMessageDto;
import com.maks.web.additionalService.IAdditionalMailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AdminMessageController {

    private final IMessageServiceService messageServiceService;
    private final IAdditionalMailService mailService;
    public static final Logger log = Logger.getLogger(AdminMessageController.class);

    @Autowired
    public AdminMessageController(IMessageServiceService messageServiceService,
                                  IAdditionalMailService mailService) {
        this.messageServiceService = messageServiceService;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/admin/message-control", method = RequestMethod.GET)
    public ModelAndView showMessagePage() {
        ModelAndView view = new ModelAndView("messageControl");
        view.addObject("messages", messageServiceService.getMessages());
        view.addObject("replyObject", new ReplyOnUserMessageDto());
        return view;
    }

    @RequestMapping(value = "/admin/message-control/reply", method = RequestMethod.POST)
    public String sendReply(@Valid @ModelAttribute("replyObject") ReplyOnUserMessageDto
                                    replyOnUserMessageDto, BindingResult result) {
        if (result.hasErrors()) {
            return "messageControl";
        }
        messageServiceService.saveAdminReply(replyOnUserMessageDto);
        mailService.replyOnUserMessage(replyOnUserMessageDto.getUserLogin(),
                replyOnUserMessageDto.getReplyBody());
        return "redirect:/admin/message-control?reply=true";
    }
}
