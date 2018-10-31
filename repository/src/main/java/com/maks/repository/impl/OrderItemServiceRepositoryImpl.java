package com.maks.repository.impl;

import com.maks.repository.IOrderItemServiceRepository;
import com.maks.repository.daoService.IAdditionalDatabaseOperationOrderItem;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderItemServiceRepositoryImpl implements IOrderItemServiceRepository {

    private final ICrudOperation<OrderItem, Long> orderItemDao;
    private final IAdditionalDatabaseOperationOrderItem<OrderItem, Long> orderItemAdditionalDao;

    @Autowired
    public OrderItemServiceRepositoryImpl(@Qualifier("orderItemDao")
                                                  ICrudOperation<OrderItem, Long> orderItemDao,
                                          @Qualifier("orderItemDao")
                                                  IAdditionalDatabaseOperationOrderItem<OrderItem, Long>
                                                  orderItemAdditionalDao) {
        this.orderItemDao = orderItemDao;
        this.orderItemAdditionalDao = orderItemAdditionalDao;
    }

    @Transactional
    @Override
    public void saveNewOrderItem(OrderItem orderItem) {
        orderItemDao.createEntity(orderItem);
    }

    @Transactional
    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemAdditionalDao.getEntities();
    }

    @Transactional
    @Override
    public void copyExistingOrderItem(Long orderItemId) {
        orderItemAdditionalDao.copyExistingEntity(orderItemId);
    }
}
