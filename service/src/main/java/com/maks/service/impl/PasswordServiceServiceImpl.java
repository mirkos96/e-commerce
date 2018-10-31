package com.maks.service.impl;

import com.maks.repository.ILoginServiceRepository;
import com.maks.repository.IPasswordServiceRepository;
import com.maks.repository.model.User;
import com.maks.service.IPasswordServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceServiceImpl implements IPasswordServiceService {

    private
    IPasswordServiceRepository passwordServiceRepository;
    private final ILoginServiceRepository loginServiceRepository;

    @Autowired
    public PasswordServiceServiceImpl(IPasswordServiceRepository passwordServiceRepository,
                                      ILoginServiceRepository loginServiceRepository) {
        this.passwordServiceRepository = passwordServiceRepository;
        this.loginServiceRepository = loginServiceRepository;
    }

    @Override
    public boolean changePassword(Long userId, String oldPassword, String newPassword,
                                  BCryptPasswordEncoder passwordEncoder) {
        if (passwordServiceRepository.getPasswordFromDatabaseAndCheckEquality(userId, oldPassword,
                passwordEncoder)) {
            passwordServiceRepository.setNewPassword(userId, newPassword);
            return true;
        }
        return false;
    }

    @Override
    public void setNewPassword(Long userId, String password) {
        passwordServiceRepository.setNewPassword(userId, password);
    }

    @Override
    public boolean resetPassword(String email, String bcryptedPass) {
        User user = checkIfUserExists(email);
        if (user != null) {
            passwordServiceRepository.resetPassword(user, bcryptedPass);
            return true;
        }
        return false;
    }

    private User checkIfUserExists(String email) {
        return loginServiceRepository.getUserForLogin(email);
    }
}
