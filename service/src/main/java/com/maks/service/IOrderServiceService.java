package com.maks.service;

import com.maks.repository.enumRole.EnumOrderStatus;
import com.maks.service.modelDto.OrderDto;
import com.maks.service.modelDto.OrderItemDto;

import java.util.List;

public interface IOrderServiceService {

    void saveNewOrder(Long userId, List<OrderItemDto> listOfDtoItemsToSave);

    List<OrderDto> getOrders(Long userId);

    List<OrderDto> getAllOrders();

    void deleteNotConfirmedOrderById(Long orderId);

    void setNewStatusToOrder(String orderStatus, Long orderId);
}
