package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_order_item_picture")
public class OrderItemPicture implements Serializable {

    public static final Long serialVersionUID = 432657L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_picture_id")
    private Long pictureId;

    @Column(name = "f_picture_name", length = 25, nullable = false)
    private String pictureName;

    public OrderItemPicture() {
    }

    public OrderItemPicture(String pictureName) {
        this.pictureName = pictureName;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public String toString() {
        return "OrderItemPicture{" +
                "pictureId=" + pictureId +
                ", pictureName='" + pictureName + '\'' +
                '}';
    }
}
