package com.maks.repository.model;

import com.maks.repository.enumRole.EnumUserRole;
import com.maks.repository.enumStatusConverter.UserRoleConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_role")
public class UserRole implements Serializable{

    public static final Long serialVersionUID = 42353465L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_role_id")
    private Long roleId;

    @Column(name = "f_role", length = 20, nullable = false)
    @Convert(converter = UserRoleConverter.class)
    private EnumUserRole userRole;

    public UserRole() {
    }

    public UserRole(EnumUserRole userRole) {
        this.userRole = userRole;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public EnumUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(EnumUserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", userRole=" + userRole +
                '}';
    }
}
