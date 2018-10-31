package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_order_item")
public class OrderItem implements Serializable{

    public static final Long serialVersionUID = 42354325L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_order_item_id")
    private Long orderItemId;

    @Column(name = "f_order_name", length = 40, nullable = false)
    private String orderName;

    @Column(name = "f_order_description", length = 150, nullable = false)
    private String orderDescription;

    @Column(name = "f_order_price", nullable = false)
    private Integer orderPrice;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "f_order_item_picture_id")
    private OrderItemPicture picture;

    public OrderItem() {
    }

    public OrderItem(String orderName, String orderDescription,
                     Integer orderPrice, OrderItemPicture picture) {
        this.orderName = orderName;
        this.orderDescription = orderDescription;
        this.orderPrice = orderPrice;
        this.picture = picture;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OrderItemPicture getPicture() {
        return picture;
    }

    public void setPicture(OrderItemPicture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderName='" + orderName + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", orderPrice=" + orderPrice +
                ", picture=" + picture +
                '}';
    }
}
