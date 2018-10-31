package com.maks.repository.impl;

import com.maks.repository.IRoleServiceRepository;
import com.maks.repository.daoService.IAdditionalDatabaseOperationUserRole;
import com.maks.repository.daoService.ICrudOperation;
import com.maks.repository.enumRole.EnumUserRole;
import com.maks.repository.model.User;
import com.maks.repository.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleServiceRepositoryImpl implements IRoleServiceRepository {

    private final ICrudOperation<User, Long> userDao;
    private final IAdditionalDatabaseOperationUserRole<UserRole, Long>
            userRoleAdditionalDao;

    @Autowired
    public RoleServiceRepositoryImpl(@Qualifier("userDao")
                                             ICrudOperation<User, Long> userDao,
                                     @Qualifier("userRoleDao")
                                             IAdditionalDatabaseOperationUserRole<UserRole, Long>
                                             userRoleAdditionalDao) {
        this.userDao = userDao;
        this.userRoleAdditionalDao = userRoleAdditionalDao;
    }

    @Transactional
    @Override
    public List<EnumUserRole> getRoles() {
        List<UserRole> userRoleList = userRoleAdditionalDao.getUserRoleEntities();
        List<EnumUserRole> enumUserRoleList = new ArrayList<>();
        for (UserRole role : userRoleList) {
            enumUserRoleList.add(role.getUserRole());
        }
        return enumUserRoleList;
    }

    @Transactional
    @Override
    public void setNewRole(EnumUserRole role, Long userId) {
        User user = getUserById(userId);
        UserRole userRole = getUserRole(role);
        user.setUserRole(userRole);
        userDao.updateEntity(user);
    }

    @Transactional
    public UserRole getUserRole(EnumUserRole userRole) {
        return userRoleAdditionalDao.getUserRoleByName(userRole);
    }

    @Transactional
    public User getUserById(Long userId) {
        return userDao.readEntity(userId);
    }
}
