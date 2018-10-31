package com.maks.web.controller;

import com.maks.service.IOrderServiceService;
import com.maks.service.modelDto.OrderDto;
import com.maks.service.modelDto.OrderItemDto;
import com.maks.service.modelDto.UserDto;
import com.maks.web.additionalService.IAdditionalOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BucketController {

    private IOrderServiceService orderService;
    private final IAdditionalOrderService additionalOrderService;
    private static final Logger log = Logger.getLogger(BucketController.class);

    @Autowired
    public BucketController(IOrderServiceService orderService, IAdditionalOrderService additionalOrderService) {
        this.orderService = orderService;
        this.additionalOrderService = additionalOrderService;
    }

    @RequestMapping(value = "/user/bucket", method = RequestMethod.GET)
    public ModelAndView showBucketPage(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("bucket");
        List<OrderItemDto> listOfItemsFromSession =
                additionalOrderService.getItemsForBucketFromSession(request);
        listOfItemsFromSession =
                additionalOrderService.optimizeEqualsNamesInList(listOfItemsFromSession);
        Integer totalPriceForItems =
                additionalOrderService.countTotalPriceForList(listOfItemsFromSession);
        UserDto userDto = getUserFromSession(request);
        Long userId = userDto.getId();
        List<OrderDto> orderDtoList = orderService.getOrders(userId);
        additionalOrderService.countTotalPriceForOrder(orderDtoList);
        log.info(orderDtoList);
        view.addObject("orderDtoList", orderDtoList);
        view.addObject("totalPrice", totalPriceForItems);
        view.addObject("listOfItems", listOfItemsFromSession);
        return view;
    }

    @RequestMapping(value = "/user/bucket/change-order", method = RequestMethod.POST)
    public String preOrderChange(HttpServletRequest request) {
        String itemName = request.getParameter("orderItemName");
        Integer amount = Integer.valueOf(request.getParameter("newAmount"));
        List<OrderItemDto> listOfItems =
                additionalOrderService.getItemsForBucketFromSession(request);
        additionalOrderService.
                optimizeAmountOfItemsInOrderList(listOfItems, itemName, amount);
        return "redirect:/user/bucket";
    }


    @RequestMapping(value = "/user/bucket/delete-order", method = RequestMethod.POST)
    public String preOrderDelete(HttpServletRequest request) {
        String orderItemName = request.getParameter("orderNameToDelete");
        List<OrderItemDto> listOfItems =
                additionalOrderService.getItemsForBucketFromSession(request);
        additionalOrderService.
                deleteItemsFromTheList(listOfItems, orderItemName);
        return "redirect:/user/bucket";
    }

    @RequestMapping(value = "/user/bucket/make-an-order", method = RequestMethod.POST)
    public String makeAnOrder(HttpServletRequest request) {
        List<OrderItemDto> listOfItemsFromSession =
                additionalOrderService.getItemsForBucketFromSession(request);
        UserDto userDto = getUserFromSession(request);
        List<OrderItemDto> listToSave = userDto.getItemDtoList();
        Long userId = userDto.getId();
        orderService.saveNewOrder(userId, listToSave);
        additionalOrderService.removeElements(listOfItemsFromSession);
        return "redirect:/user/bucket";
    }

    @RequestMapping(value = "/user/bucket/delete-not-confirmed-order", method = RequestMethod.POST)
    public String deleteNotConfirmedOrder(HttpServletRequest request) {
        Long orderId = Long.parseLong(request.getParameter("orderIdToDelete"));
        log.info(orderId + " order ID");
        orderService.deleteNotConfirmedOrderById(orderId);
        return "redirect:/user/bucket";
    }

    private UserDto getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UserDto) session.getAttribute("userDto");
    }
}
