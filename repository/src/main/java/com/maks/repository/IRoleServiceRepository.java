package com.maks.repository;

import com.maks.repository.enumRole.EnumUserRole;

import java.util.List;

public interface IRoleServiceRepository {

    List<EnumUserRole> getRoles();

    void setNewRole(EnumUserRole userRole, Long userId);
}
