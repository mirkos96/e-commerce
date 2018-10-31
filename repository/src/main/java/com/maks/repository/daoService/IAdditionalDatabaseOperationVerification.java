package com.maks.repository.daoService;

public interface IAdditionalDatabaseOperationVerification<E, I> {

    E getVerificationByToken(String verificationToken);
}
