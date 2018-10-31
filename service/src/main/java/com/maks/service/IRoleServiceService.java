package com.maks.service;

import com.maks.service.modelDto.RoleDto;

import java.util.List;

public interface IRoleServiceService {

    List<RoleDto> getRoles();

    void setNewRole(String role, Long userId);
}
