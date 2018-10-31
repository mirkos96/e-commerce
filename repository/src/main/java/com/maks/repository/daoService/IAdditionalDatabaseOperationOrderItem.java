package com.maks.repository.daoService;

import java.util.List;

public interface IAdditionalDatabaseOperationOrderItem<E, I> {

    List<E> getEntities();

    void copyExistingEntity(I i);
}
