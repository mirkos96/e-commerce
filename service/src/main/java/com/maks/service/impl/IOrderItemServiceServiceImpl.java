package com.maks.service.impl;

import com.maks.repository.IOrderItemServiceRepository;
import com.maks.service.IOrderItemServiceService;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.service.modelDto.OrderItemDto;
import com.maks.service.modelDto.UploadedOrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrderItemServiceServiceImpl implements IOrderItemServiceService {

    private final IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities;
    private final IOrderItemServiceRepository orderItemServiceRepository;

    @Autowired
    public IOrderItemServiceServiceImpl(IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities, IOrderItemServiceRepository orderItemServiceRepository) {
        this.convertDtoAndPersistentEntities = convertDtoAndPersistentEntities;
        this.orderItemServiceRepository = orderItemServiceRepository;
    }

    @Override
    public void saveNewOrderItem(UploadedOrderItemDto uploadedOrderItemDto) {
        orderItemServiceRepository.saveNewOrderItem(
                convertDtoAndPersistentEntities.convertUploadedDtoToEntityOrderItem(
                        uploadedOrderItemDto));
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        return convertDtoAndPersistentEntities.convertOrderItemsIntoDto(
                orderItemServiceRepository.getAllOrderItems());
    }

    @Override
    public void copyExistingOrderItem(Long orderId) {
        orderItemServiceRepository.copyExistingOrderItem(orderId);
    }
}
