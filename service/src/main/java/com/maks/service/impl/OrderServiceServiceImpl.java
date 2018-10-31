package com.maks.service.impl;

import com.maks.repository.IOrderServiceRepository;
import com.maks.service.IOrderServiceService;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.service.modelDto.OrderDto;
import com.maks.service.modelDto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceServiceImpl implements IOrderServiceService {

    private IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities;
    private IOrderServiceRepository orderServiceRepository;

    @Autowired
    public OrderServiceServiceImpl(IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities, IOrderServiceRepository orderServiceRepository) {
        this.convertDtoAndPersistentEntities = convertDtoAndPersistentEntities;
        this.orderServiceRepository = orderServiceRepository;
    }

    @Override
    public void saveNewOrder(Long userId, List<OrderItemDto> listOfDtoItemsToSave) {
        orderServiceRepository.saveNewOrder(userId,
                convertDtoAndPersistentEntities.convertDtoToOrderItems(listOfDtoItemsToSave));
    }

    @Override
    public List<OrderDto> getOrders(Long userId) {
        return convertDtoAndPersistentEntities.convertOrderEntityToDto
                (orderServiceRepository.getOrders(userId));
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return convertDtoAndPersistentEntities.convertOrderEntityToDto(
                orderServiceRepository.getAllOrders());
    }

    @Override
    public void deleteNotConfirmedOrderById(Long orderId) {
        orderServiceRepository.deleteNotConfirmedOrderById(orderId);
    }

    @Override
    public void setNewStatusToOrder(String orderStatus, Long orderId) {
        orderServiceRepository.setNewStatusToOrder(
                convertDtoAndPersistentEntities.convertStringStatusToEnumOrder(orderStatus),
                orderId);
    }
}
