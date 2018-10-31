package com.maks.repository;

import com.maks.repository.enumRole.EnumOrderStatus;
import com.maks.repository.model.Order;
import com.maks.repository.model.OrderItem;

import java.util.List;

public interface IOrderServiceRepository {

    void saveNewOrder(Long userId, List<OrderItem> listOfItemsToSave);

    List<Order> getOrders(Long userId);

    List<Order> getAllOrders();

    void deleteNotConfirmedOrderById(Long orderId);

    void setNewStatusToOrder(EnumOrderStatus orderStatus, Long orderId);
}
