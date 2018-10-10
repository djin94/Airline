package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.web.dao.RoleRepository;
import com.foxminded.airline.web.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.foxminded.airline.domain.entity.User appUser = userRepository.findByLogin(login);
        if (appUser == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        }
        // [ROLE_USER, ROLE_ADMIN,..]
        GrantedAuthority authority = new SimpleGrantedAuthority(appUser.getRole().getName());

        UserDetails userDetails = (UserDetails) new User(appUser.getLogin(), //
                appUser.getPassword());

        return userDetails;
    }
}
