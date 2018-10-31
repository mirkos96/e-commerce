package com.maks.repository.daoService;

public interface ICrudOperation<E, I> {

    void createEntity(E e);

    E readEntity(I i);

    void updateEntity(E e);

    void deleteEntity(E e);


}
