package com.maks.service.impl;

import com.maks.repository.IRoleServiceRepository;
import com.maks.service.IRoleServiceService;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.service.modelDto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceServiceImpl implements IRoleServiceService {

    private final IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities;
    private final IRoleServiceRepository roleServiceRepository;

    @Autowired
    public RoleServiceServiceImpl(IRoleServiceRepository roleServiceRepository, IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities) {
        this.roleServiceRepository = roleServiceRepository;
        this.convertDtoAndPersistentEntities = convertDtoAndPersistentEntities;
    }

    @Override
    public List<RoleDto> getRoles() {
        return convertDtoAndPersistentEntities.convertEnumRoleToString(
                roleServiceRepository.getRoles());
    }

    @Override
    public void setNewRole(String role, Long userId) {
        roleServiceRepository.setNewRole(convertDtoAndPersistentEntities
                .convertStringToEnumUserRole(role), userId);
    }
}
