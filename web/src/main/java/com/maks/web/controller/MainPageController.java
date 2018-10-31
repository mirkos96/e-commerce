package com.maks.web.controller;

import com.maks.service.IPaginationServiceService;
import com.maks.service.modelDto.OrderItemDto;
import com.maks.service.modelDto.UserDto;
import com.maks.web.additionalService.IAdditionalOrderService;
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
import java.util.List;
/*
        HOW TO ACHIEVE PAGINATION:
1) Get needed (INTEGER) parameter from the page, that indicates
current number of page!
2) Get the (INTEGER) amount of all orderItems in the database
3) Depends on the amount of displaying orders on the view
we should get (INTEGER) number of amount of total pages,
that also depends on amount of OrderItems situated in database
4) Get right amount of orderItems from database, according to the page
and to the amount of displaying items on it.
5) Repeat these actions every time page is reloaded.
*/

@Controller
public class MainPageController {

    private static final Logger log = Logger.getLogger(MainPageController.class);
    private IPaginationServiceService paginationService;
    private final IAdditionalSessionService additionalSessionService;
    private final IAdditionalOrderService additionalOrderService;

    @Autowired
    public MainPageController(IPaginationServiceService paginationService, IAdditionalSessionService additionalSessionService, IAdditionalOrderService additionalOrderService) {
        this.paginationService = paginationService;
        this.additionalSessionService = additionalSessionService;
        this.additionalOrderService = additionalOrderService;
    }

    @RequestMapping(value = "/user/main-page", method = RequestMethod.GET)
    public ModelAndView showMainPage(HttpServletRequest request) {
        String currentPageNum = request.getParameter("pageNum");
        Integer pageNumber = setPageNumber(currentPageNum);
        List<OrderItemDto> orderItemDtoList =
                paginationService.getCertainAmountOfItemsForPage(pageNumber);
        Integer amountOfPages =
                paginationService.countTotalNumberOfPages();
        ModelAndView view = new ModelAndView("mainPage");
        view.addObject("orderItemDto", new OrderItemDto());
        view.addObject("numberOfPages", amountOfPages);
        view.addObject("items", orderItemDtoList);
        return view;
    }

    private Integer setPageNumber(String pageNum) {
        Integer pageNumber = 1;
        if (pageNum == null) {
            return pageNumber;
        } else
            return Integer.valueOf(pageNum);
    }

    @RequestMapping(value = "/user/main-page/add-item", method = RequestMethod.POST)
    public String addNewOrderItemToTheList(HttpServletRequest request,
                                           @Valid @ModelAttribute("orderItemDto")
                                                   OrderItemDto orderItemDto,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/user/main-page";
        }
        String currentPageNum = request.getParameter("pageNum");
        Integer pageNumber = setPageNumber(currentPageNum);
        UserDto userDto = additionalSessionService.getUserFromSession(request);
        List<OrderItemDto> list = userDto.getItemDtoList();
        list = additionalOrderService.putNewOrdersIntoList(orderItemDto, list);
        additionalSessionService.putUserBackToSession(userDto, list, request);
        return "redirect:/user/main-page?pageNum=" + pageNumber;
    }
}
