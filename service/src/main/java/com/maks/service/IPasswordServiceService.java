package com.maks.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface IPasswordServiceService {

    boolean changePassword(Long userId, String oldPassword, String newPassword,
                           BCryptPasswordEncoder passwordEncoder);

    void setNewPassword(Long userId, String password);

    boolean resetPassword(String email, String tempPass);

}
