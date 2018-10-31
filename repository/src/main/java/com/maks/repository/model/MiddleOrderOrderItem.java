package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_middle_order_order_item")
public class MiddleOrderOrderItem implements Serializable {

    public static final Long serialVersionUID = 43253L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_middle_id")
    private Long middleId;

    @Column(name = "f_order_id")
    private long orderId;

    @Column(name = "f_order_item_id")
    private Long orderItemId;

    public MiddleOrderOrderItem() {
    }

    public MiddleOrderOrderItem(long orderId, Long orderItemId) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
    }

    public Long getMiddleId() {
        return middleId;
    }

    public void setMiddleId(Long middleId) {
        this.middleId = middleId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    @Override
    public String toString() {
        return "MiddleOrdeOrderItem{" +
                "middleId=" + middleId +
                ", orderId=" + orderId +
                ", orderItemId=" + orderItemId +
                '}';
    }
}
