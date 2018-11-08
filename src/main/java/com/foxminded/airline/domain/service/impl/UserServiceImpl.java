package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.domain.entity.Role;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.dto.UserDTO;
import com.foxminded.airline.utils.UserConverter;
import com.foxminded.airline.web.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserConverter userConverter;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER.getRole());
        userRepository.save(user);
    }

    @Override
    public User findOrCreateUserFromUserDTO(UserDTO userDTO) {
        return userRepository.findByPassportNumber(userDTO.getPassportNumber())
                .orElseGet(() -> userConverter.createUserFromUserDTO(userDTO));
    }

    @Override
    public void editPassportData(User user, UserDTO userDTO) {
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setPatronym(userDTO.getPatronym());
        user.setPassportNumber(userDTO.getPassportNumber());
    }

    @Override
    public User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        }
        return null;
    }
}
