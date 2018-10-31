package com.maks.service.modelDto;

import com.maks.repository.enumRole.EnumOrderStatus;

import java.util.Date;
import java.util.List;

public class OrderDto {

    private Long orderId;
    private Integer orderNumber;
    private List<OrderItemDto> pizzas;
    private EnumOrderStatus orderStatus;
    private Date date;
    private Integer price;

    public OrderDto() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderItemDto> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<OrderItemDto> pizzas) {
        this.pizzas = pizzas;
    }

    public EnumOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EnumOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", orderNumber=" + orderNumber +
                ", pizzas=" + pizzas +
                ", orderStatus=" + orderStatus +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
