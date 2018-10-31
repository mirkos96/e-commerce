package com.maks.web.controller;

import com.maks.service.IOrderServiceService;
import com.maks.service.modelDto.OrderDto;

import com.maks.web.additionalService.IAdditionalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminOrderController {

    private final IOrderServiceService orderServiceService;
    private final IAdditionalOrderService additionalOrderService;

    @Autowired
    public AdminOrderController(IOrderServiceService orderServiceService, IAdditionalOrderService additionalOrderService) {
        this.orderServiceService = orderServiceService;
        this.additionalOrderService = additionalOrderService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/order-status-control", method = RequestMethod.GET)
    public ModelAndView showControlOverOrders() {
        ModelAndView view = new ModelAndView("orderControl");
        List<OrderDto> orderDtoList = orderServiceService.getAllOrders();
        additionalOrderService.countTotalPriceForOrder(orderDtoList);
        view.addObject("orderList", orderDtoList);
        return view;
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @RequestMapping(value = "/admin/order-status-control/set-new-status",
            method = RequestMethod.POST)
    public String setNewStatusToOrder(HttpServletRequest request) {
        String orderStatus = request.getParameter("orderStatus");
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        orderServiceService.setNewStatusToOrder(orderStatus, orderId);
        return "redirect:/admin/order-status-control";
    }
}
