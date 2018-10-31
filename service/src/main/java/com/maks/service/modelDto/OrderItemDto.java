package com.maks.service.modelDto;

public class OrderItemDto {

    private Long orderItemId;
    private String orderItemName;
    private String orderItemDescription;
    private Integer orderItemPrice;
    private String orderItemPictureName;
    private Integer orderItemAmount;

    public OrderItemDto() {
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderItemName() {
        return orderItemName;
    }

    public void setOrderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }

    public String getOrderItemDescription() {
        return orderItemDescription;
    }

    public void setOrderItemDescription(String orderItemDescription) {
        this.orderItemDescription = orderItemDescription;
    }

    public Integer getOrderItemPrice() {
        return orderItemPrice;
    }

    public void setOrderItemPrice(Integer orderItemPrice) {
        this.orderItemPrice = orderItemPrice;
    }

    public String getOrderItemPictureName() {
        return orderItemPictureName;
    }

    public void setOrderItemPictureName(String orderItemPictureName) {
        this.orderItemPictureName = orderItemPictureName;
    }

    public Integer getOrderItemAmount() {
        return orderItemAmount;
    }

    public void setOrderItemAmount(Integer orderItemAmount) {
        this.orderItemAmount = orderItemAmount;
    }


    @Override
    public String toString() {
        return "OrderItemDto{" +
                "orderItemId=" + orderItemId +
                ", orderItemName='" + orderItemName + '\'' +
                ", orderItemDescription='" + orderItemDescription + '\'' +
                ", orderItemPrice=" + orderItemPrice +
                ", orderItemPictureName='" + orderItemPictureName + '\'' +
                ", orderItemAmount=" + orderItemAmount +
                '}';
    }
}
