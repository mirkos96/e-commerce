package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User implements Serializable {

    public static final Long serialVersionUID = 5345534L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    private Long userId;

    @Column(name = "username", length = 25, nullable = false)
    private String userLogin;

    @Column(name = "f_password", length = 100, nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "f_account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "f_verification_id")
    private Verification verification;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "f_user_information_id")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "f_role_id")
    private UserRole userRole;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orderList;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User() {
    }

    public User(String userLogin, String password, Account account,
                Verification verification, UserInfo userInfo,
                UserRole userRole, List<Order> orderList, List<Comment> comments) {
        this.userLogin = userLogin;
        this.password = password;
        this.account = account;
        this.verification = verification;
        this.userInfo = userInfo;
        this.userRole = userRole;
        this.orderList = orderList;
        this.comments = comments;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                ", password='" + password + '\'' +
                ", account=" + account +
                ", verification=" + verification +
                ", userInfo=" + userInfo +
                ", userRole=" + userRole +
                ", orderList=" + orderList +
                ", comments=" + comments +
                '}';
    }
}