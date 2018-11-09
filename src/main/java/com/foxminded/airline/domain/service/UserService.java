package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    void save(User user);

    void editPassportData(User user, UserDTO userDTO);

    User getCurrentUser();

    Optional<User> findUserByLogin(String login);
}
