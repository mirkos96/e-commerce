package com.maks.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_verification")
public class Verification implements Serializable {

    public static final Long serialVersionUID = 45643532L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_verification_id")
    private Long verificationId;

    @Column(name = "f_verification_token")
    private String verificationToken;

    public Verification() {
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    @Override
    public String toString() {
        return "Verification{" +
                "verificationId=" + verificationId +
                ", verificationToken='" + verificationToken + '\'' +
                '}';
    }
}
