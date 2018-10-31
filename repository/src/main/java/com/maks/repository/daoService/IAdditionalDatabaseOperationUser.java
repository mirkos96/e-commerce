package com.maks.repository.daoService;

import com.maks.repository.model.Verification;

import java.util.List;

public interface IAdditionalDatabaseOperationUser<E, I> {

    E getByName(String name);

    List<E> getListOfEntities();

    void deleteById(I i);

    E getByVerificationToken(Verification verification);

}
