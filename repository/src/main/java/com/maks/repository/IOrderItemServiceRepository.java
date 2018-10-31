package com.maks.repository;

import com.maks.repository.model.OrderItem;

import java.util.List;

public interface IOrderItemServiceRepository {

    void saveNewOrderItem(OrderItem orderItem);

    List<OrderItem> getAllOrderItems();

    void copyExistingOrderItem(Long orderItemId);

}
