package com.maks.web.controller;

import com.maks.service.IOrderItemServiceService;
import com.maks.service.modelDto.UploadedOrderItemDto;
import com.maks.web.additionalService.IAdditionalOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/*
ADD ANOTHER ONE CHECK FOR UPLOADED ENTITY, FOR UNIQUENESS IN FOLDER,
AND AS WELL FOR UNIQUENESS IN DATABASE.
 */

@Controller
public class AdminOrderItemController {

    private final Validator validator;
    private final IAdditionalOrderItemService additionalOrderItemService;
    private final IOrderItemServiceService orderItemServiceService;

    @Autowired
    public AdminOrderItemController(@Qualifier("uploadedOrderItemValidator")
                                            Validator validator,
                                    IAdditionalOrderItemService orderItemService, IOrderItemServiceService orderItemServiceService) {
        this.validator = validator;
        this.additionalOrderItemService = orderItemService;
        this.orderItemServiceService = orderItemServiceService;
    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(validator);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/order-item/add", method = RequestMethod.GET)
    public ModelAndView showAdminOrderItemControlPage() {
        ModelAndView view = new ModelAndView("orderItemAdd");
        view.addObject("uploadOrderItem", new UploadedOrderItemDto());
        view.addObject("orderItemDtoList", orderItemServiceService.getAllOrderItems());
        return view;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/order-item/add-new", method = RequestMethod.POST)
    public String uploadNewOrderItem(@Valid @ModelAttribute("uploadOrderItem")
                                             UploadedOrderItemDto uploadedOrderItemDto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return "orderItemAdd";
        }
        if (uploadedOrderItemDto.getFile().isEmpty()) {
            return "redirect:/admin/order-item/add?action=false";
        }
        additionalOrderItemService.saveNewPictureToFolder(uploadedOrderItemDto);
        orderItemServiceService.saveNewOrderItem(uploadedOrderItemDto);
        return "redirect:/admin/order-item/add?action=true";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/order-item/copy", method = RequestMethod.POST)
    public String copyExistingOrderItem(HttpServletRequest request) {
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        orderItemServiceService.copyExistingOrderItem(orderId);
        return "redirect:/admin/order-item/add?action=copyTrue";
    }
}
