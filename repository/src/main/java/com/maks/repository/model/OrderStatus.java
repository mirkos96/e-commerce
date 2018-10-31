package com.maks.repository.model;

import com.maks.repository.enumRole.EnumOrderStatus;
import com.maks.repository.enumStatusConverter.OrderStatusConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_order_status")
public class OrderStatus implements Serializable {

    public static final Long serialVersionUID = 413245235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_order_status_id")
    private Long statusId;

    @Column(name = "f_order_status", nullable = false, length = 20)
    @Convert(converter = OrderStatusConverter.class)
    private EnumOrderStatus orderStatus;

    public OrderStatus() {
    }

    public OrderStatus(EnumOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public EnumOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EnumOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "statusId=" + statusId +
                ", orderStatus=" + orderStatus +
                '}';
    }
}

