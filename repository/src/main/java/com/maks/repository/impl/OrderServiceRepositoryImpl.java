package com.maks.repository.impl;

import com.maks.repository.IOrderServiceRepository;
import com.maks.repository.daoService.IAdditionalDatabaseOperationOrder;
import com.maks.repository.daoService.IAdditionalDatabaseOperationOrderStatus;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.enumRole.EnumOrderStatus;
import com.maks.repository.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
public class OrderServiceRepositoryImpl implements IOrderServiceRepository {

    public static final Logger log = Logger.getLogger(OrderServiceRepositoryImpl.class);
    private final ICrudOperation<User, Long> userDao;
    private final ICrudOperation<Order, Long> orderDao;
    private final IAdditionalDatabaseOperationOrder<Order, Long> orderAdditionalDao;
    private final IAdditionalDatabaseOperationOrderStatus<OrderStatus, Long>
            orderStatusAdditionalDao;

    @Autowired
    public OrderServiceRepositoryImpl(@Qualifier("userDao")
                                              ICrudOperation<User, Long> userDao,
                                      @Qualifier("orderDaoImpl")
                                              ICrudOperation<Order, Long> orderDao,
                                      @Qualifier("orderDaoImpl")
                                              IAdditionalDatabaseOperationOrder<Order, Long> orderAdditionalDao,
                                      @Qualifier("orderStatusDao")
                                              IAdditionalDatabaseOperationOrderStatus<OrderStatus, Long>
                                              orderStatusAdditionalDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.orderAdditionalDao = orderAdditionalDao;
        this.orderStatusAdditionalDao = orderStatusAdditionalDao;
    }


    @Transactional
    @Override
    public void saveNewOrder(Long userId, List<OrderItem> listOfItemsToSave) {
        Random rd = new Random();
        Order order = new Order();
        order.setOrderNumber(rd.nextInt(10000000));
        order.setOrderOrderItemList(listOfItemsToSave);
        order.setOrderStatus(getStandardOrderStatus());
        order.setUser(userDao.readEntity(userId));
        orderDao.createEntity(order);
    }

    @Transactional
    @Override
    public List<Order> getOrders(Long userId) {
        return orderAdditionalDao.getEntities(userId);
    }

    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @Override
    public List<Order> getAllOrders() {
        return orderAdditionalDao.getEntities();
    }

    @Transactional
    @Override
    public void deleteNotConfirmedOrderById(Long orderId) {
        orderAdditionalDao.deleteById(orderId);
    }

    @Transactional
    @Override
    public void setNewStatusToOrder(EnumOrderStatus orderStatus, Long orderId) {
        Order order = orderDao.readEntity(orderId);
        order.setOrderStatus(getOrderStatusByName(orderStatus));
        orderDao.updateEntity(order);
    }

    @Transactional
    public OrderStatus getStandardOrderStatus() {
        return orderStatusAdditionalDao.getStandardStatus();
    }

    @Transactional
    public OrderStatus getOrderStatusByName(EnumOrderStatus orderStatus) {
        return orderStatusAdditionalDao.getByName(orderStatus);
    }
}
