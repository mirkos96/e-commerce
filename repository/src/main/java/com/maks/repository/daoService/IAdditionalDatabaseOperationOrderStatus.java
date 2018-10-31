package com.maks.repository.daoService;

import com.maks.repository.enumRole.EnumOrderStatus;

public interface IAdditionalDatabaseOperationOrderStatus<E, I> {

    E getByName(EnumOrderStatus orderStatusName);

    E getStandardStatus();
}
