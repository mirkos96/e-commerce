package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_order")
public class Order implements Serializable {

    public static final Long serialVersionUID = 4234L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_order_id")
    private Long id;

    @Column(name = "f_order_number", nullable = false)
    private Integer orderNumber;

    @OneToOne
    @JoinColumn(name = "f_order_status_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "f_user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_middle_order_order_item",
            joinColumns = {@JoinColumn(name = "f_order_id")},
            inverseJoinColumns = {@JoinColumn(name = "f_order_item_id")})
    private List<OrderItem> orderOrderItemList;

    public Order() {
    }

    public Order(Integer orderNumber, OrderStatus orderStatus,
                 User user, List<OrderItem> orderOrderItemList) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.user = user;
        this.orderOrderItemList = orderOrderItemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderOrderItemList() {
        return orderOrderItemList;
    }

    public void setOrderOrderItemList(List<OrderItem> orderOrderItemList) {
        this.orderOrderItemList = orderOrderItemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber=" + orderNumber +
                ", orderStatus=" + orderStatus +
                ", user=" + user +
                ", orderOrderItemList=" + orderOrderItemList +
                '}';
    }
}
