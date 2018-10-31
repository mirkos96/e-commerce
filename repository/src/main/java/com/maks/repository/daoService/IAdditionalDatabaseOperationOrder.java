package com.maks.repository.daoService;

import java.util.List;

public interface IAdditionalDatabaseOperationOrder<E, I> {

    List<E> getEntities(I i);

    List<E> getEntities();

    void deleteById(I i);
}
