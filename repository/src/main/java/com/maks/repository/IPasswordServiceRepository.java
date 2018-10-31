package com.maks.repository;

import com.maks.repository.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface IPasswordServiceRepository {

    boolean getPasswordFromDatabaseAndCheckEquality(Long userId, String oldPassword,
                                                    BCryptPasswordEncoder passwordEncoder);

    void setNewPassword(Long userId, String newPassword);

    void resetPassword(User user, String tempPass);
}
