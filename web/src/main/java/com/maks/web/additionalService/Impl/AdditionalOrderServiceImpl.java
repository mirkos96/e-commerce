package com.maks.web.additionalService.Impl;

import com.maks.service.modelDto.OrderDto;
import com.maks.service.modelDto.OrderItemDto;
import com.maks.service.modelDto.UserDto;
import com.maks.web.additionalService.IAdditionalOrderService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class AdditionalOrderServiceImpl implements IAdditionalOrderService {

    @Override
    public void countTotalPriceForOrder(List<OrderDto> orderDtoList) {
        List<OrderItemDto> itemDtoList;
        for (OrderDto order : orderDtoList) {
            itemDtoList = order.getPizzas();
            Integer totalPrice = 0;
            for (OrderItemDto itemDto : itemDtoList) {
                totalPrice = totalPrice + itemDto.getOrderItemPrice();
                order.setPrice(totalPrice);
            }
        }
    }

    @Override
    public Integer countTotalPriceForList(List<OrderItemDto> list) {
        Integer totalPrice = 0;
        for (OrderItemDto itemDto : list) {
            totalPrice = totalPrice + (itemDto.getOrderItemAmount() * itemDto.getOrderItemPrice());
        }
        return totalPrice;
    }

    @Override
    public void optimizeAmountOfItemsInOrderList(List<OrderItemDto> itemDtoList, String itemName, Integer newAmount) {
        for (OrderItemDto itemDto : itemDtoList) {
            if (itemDto.getOrderItemName().equals(itemName)) {
                itemDto.setOrderItemAmount(newAmount);
            }
        }
    }

    @Override
    public List<OrderItemDto> optimizeEqualsNamesInList(List<OrderItemDto> listToOptimize) {
        for (int i = 0; i < listToOptimize.size(); i++) {
            for (int j = listToOptimize.indexOf(listToOptimize.get(i)) + 1;
                 j < listToOptimize.size(); j++) {
                if (listToOptimize.get(i).getOrderItemName().equals(
                        listToOptimize.get(j).getOrderItemName())) {
                    listToOptimize.get(i).setOrderItemAmount(
                            listToOptimize.get(i).getOrderItemAmount() +
                                    listToOptimize.get(j).getOrderItemAmount());
                    listToOptimize.remove(j);
                }
            }
        }
        return listToOptimize;
    }

    @Override
    public void deleteItemsFromTheList(List<OrderItemDto> itemDtoList,
                                       String itemName) {
        itemDtoList.removeIf(orderItemDto -> orderItemDto
                .getOrderItemName().equals(itemName));
    }

    @Override
    public void removeElements(List<OrderItemDto> itemDtoList) {
        itemDtoList.clear();
    }

    @Override
    public List<OrderItemDto> getItemsForBucketFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        return userDto.getItemDtoList();
    }

    @Override
    public List<OrderItemDto> putNewOrdersIntoList(OrderItemDto orderItemDtoFromPage,
                                                   List<OrderItemDto> listFromSessionUser) {
        listFromSessionUser.add(orderItemDtoFromPage);
        return listFromSessionUser;
    }
}
