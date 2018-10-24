package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User createUserFromUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPatronym(userDTO.getPatronym());
        user.setPassportNumber(userDTO.getPassportNumber());
        return user;
    }

    public UserDTO createUserDTOFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPatronym(user.getPatronym());
        userDTO.setPassportNumber(user.getPassportNumber());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }
}
