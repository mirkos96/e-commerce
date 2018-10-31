package com.maks.service.modelDto;

public class RoleDto {

    private String userRole;

    public RoleDto() {
    }

    public RoleDto(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "userRole='" + userRole + '\'' +
                '}';
    }
}
