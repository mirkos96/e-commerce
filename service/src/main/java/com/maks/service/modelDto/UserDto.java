package com.maks.service.modelDto;

import com.maks.service.validation.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDto {

    private Long id;
    @LogIn
    @UniqueLogin
    private String userLogin;
    @Password
    private String password;
    @NameSurname
    private String nameSurname;
    @Phone
    private String phone;
    @Address
    private String address;
    private List<OrderItemDto> itemDtoList;
    private boolean isBlocked;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderItemDto> getItemDtoList() {
        return itemDtoList;
    }

    public void setItemDtoList(List<OrderItemDto> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", password='" + password + '\'' +
                ", nameSurname='" + nameSurname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", itemDtoList=" + itemDtoList +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
