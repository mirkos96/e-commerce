package com.maks.repository.daoService;

import com.maks.repository.enumRole.EnumUserRole;

import java.util.List;

public interface IAdditionalDatabaseOperationUserRole<E, I> {

    List<E> getUserRoleEntities();

    E getUserRoleByName(EnumUserRole userRole);

    E getStandardUserRole();
}
