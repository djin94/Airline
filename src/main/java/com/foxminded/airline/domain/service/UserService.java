package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.web.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    String cryptPassword(String password);

    User save(User user);

    void editPassportData(User user, UserDTO userDTO);

    User getCurrentUser();

    Optional<User> findUserByLogin(String login);
}
