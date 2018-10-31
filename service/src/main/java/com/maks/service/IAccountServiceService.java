package com.maks.service;

public interface IAccountServiceService {

    boolean checkIfUserWithSuchTokenExists(String verificationToken);

    void blockUser(Long userId);

    void unblockUser(Long userId);
}
