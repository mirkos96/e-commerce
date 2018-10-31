package com.maks.web.configuration;

import com.maks.repository.daoService.IAdditionalDatabaseOperationUser;
import com.maks.repository.enumRole.EnumUserRole;
import com.maks.repository.model.User;
import com.maks.service.modelDto.OrderItemDto;
import com.maks.service.modelDto.UserDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final Logger log = Logger.getLogger(CustomAuthenticationSuccessHandler.class);
    private final IAdditionalDatabaseOperationUser<User, Long> userAdditionalDao;

    @Autowired
    public CustomAuthenticationSuccessHandler(@Qualifier("userDao")
                                                      IAdditionalDatabaseOperationUser<User, Long> userAdditionalDao) {
        this.userAdditionalDao = userAdditionalDao;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        String credentialLogin = SecurityContextHolder.getContext().
                getAuthentication().getName();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        User user = getUser(credentialLogin);
        if (redirectAccountIfNeeded(user)) {
            log.info(user.getUserRole().getUserRole().name() + " ROLE FROM USER");
            for (GrantedAuthority authority : authorityCollection) {
                log.info(authority.getAuthority() + " ROLE FROM AUTHORITY");
                if (authority.toString().equals(EnumUserRole.ROLE_USER.name())) {
                    placeUserIntoSession(request, credentialLogin);
                    response.sendRedirect("/user/main-page");
                } else if (authority.toString().equals(EnumUserRole.ROLE_ADMIN.name())
                        || authority.toString().equals(EnumUserRole.ROLE_SUPERADMIN.name())) {
                    placeUserIntoSession(request, credentialLogin);
                    response.sendRedirect("/admin/admin-page");
                }
            }
        } else {
            response.sendRedirect("/login?account=blockedNotActivated");
        }
    }


    private void placeUserIntoSession(HttpServletRequest request, String userCredential) {
        com.maks.repository.model.User userFromDatabase = getUser(userCredential);
        UserDto userDto = new UserDto();
        List<OrderItemDto> list = new ArrayList<>();
        userDto.setItemDtoList(list);
        userDto.setUserLogin(userFromDatabase.getUserLogin());
        userDto.setNameSurname(userFromDatabase.getUserInfo().getNameSurname());
        userDto.setId(userFromDatabase.getUserId());
        userDto.setAddress(userFromDatabase.getUserInfo().getUserAddress());
        userDto.setPhone(userFromDatabase.getUserInfo().getUserPhone());
        HttpSession session = request.getSession();
        session.setAttribute("userDto", userDto);
    }

    private com.maks.repository.model.User getUser(String userCredential) {
        return userAdditionalDao.getByName(userCredential);
    }

    private Boolean checkIfAccountIsActivated(User user) {
        return user.getAccount().getActivated();
    }

    private Boolean checkIfAccountIsBlocked(User user) {
        return user.getAccount().getBlocked();
    }

    private boolean redirectAccountIfNeeded(User user)
            throws IOException {
        return checkIfAccountIsActivated(user) && !checkIfAccountIsBlocked(user);
    }
}
