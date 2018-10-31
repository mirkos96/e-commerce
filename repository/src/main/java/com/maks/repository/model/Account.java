package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_account")
public class Account implements Serializable{

    public static final Long serialVersionUID = 567878976745L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_account_id")
    private Long accountId;

    @Column(name = "f_is_activated")
    private Boolean isActivated;

    @Column(name = "f_is_blocked")
    private Boolean isBlocked;

    public Account() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", isActivated=" + isActivated +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
