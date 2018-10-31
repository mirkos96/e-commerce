package com.maks.service;

import com.maks.service.modelDto.OrderItemDto;
import com.maks.service.modelDto.UploadedOrderItemDto;

import java.util.List;

public interface IOrderItemServiceService {

    void saveNewOrderItem(UploadedOrderItemDto uploadedOrderItemDto);

    List<OrderItemDto> getAllOrderItems();

    void copyExistingOrderItem(Long orderId);
}
