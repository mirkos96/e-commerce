package com.maks.web.additionalService;

import com.maks.service.modelDto.OrderDto;
import com.maks.service.modelDto.OrderItemDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAdditionalOrderService {

    void countTotalPriceForOrder(List<OrderDto> orderDtoList);

    Integer countTotalPriceForList(List<OrderItemDto> list);

    void optimizeAmountOfItemsInOrderList(List<OrderItemDto> itemDtoList,
                                          String itemName, Integer newAmount);

    List<OrderItemDto> optimizeEqualsNamesInList(List<OrderItemDto>
                                                         listToOptimize);

    void deleteItemsFromTheList(List<OrderItemDto> itemDtoList,
                                String itemName);

    void removeElements(List<OrderItemDto> itemDtoList);

    List<OrderItemDto> getItemsForBucketFromSession(HttpServletRequest request);

    List<OrderItemDto> putNewOrdersIntoList(OrderItemDto orderItemDtoFromPage,
                                            List<OrderItemDto> listFromSessionUser);


}
