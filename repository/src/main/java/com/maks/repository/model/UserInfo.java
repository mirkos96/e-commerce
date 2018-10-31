package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_user_information")
public class UserInfo implements Serializable{

    public static final Long serialVersionUID = 5435435232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_user_info_id")
    private Long userInfoId;

    @Column(name = "f_name_surname", length = 25, nullable = false)
    private String nameSurname;

    @Column(name = "f_phone", length = 20, nullable = false)
    private String userPhone;

    @Column(name = "f_address", length = 35, nullable = false)
    private String userAddress;

    public UserInfo() {
    }

    public UserInfo(String nameSurname, String userPhone, String userAddress) {
        this.nameSurname = nameSurname;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", nameSurname='" + nameSurname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
