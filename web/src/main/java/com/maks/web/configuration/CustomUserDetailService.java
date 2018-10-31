package com.maks.web.configuration;

import com.maks.repository.model.User;
import com.maks.service.ILoginServiceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    public static final Logger log = Logger.getLogger(CustomUserDetailService.class);
    private ILoginServiceService loginServiceService;

    @Autowired
    public CustomUserDetailService(ILoginServiceService loginServiceService) {
        this.loginServiceService = loginServiceService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = loginServiceService.getUserForLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getUserRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUserLogin(),
                user.getPassword(), authorities);
    }
}
