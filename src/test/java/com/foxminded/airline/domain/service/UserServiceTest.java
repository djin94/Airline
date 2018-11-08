package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Role;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.dto.UserDTO;
import com.foxminded.airline.utils.UserConverter;
import com.foxminded.airline.web.dao.UserRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.core.Authentication;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserConverter userConverters;

    @MockBean
    private Authentication authentication;

    private User user;
    private UserDTO userDTO;
    private String login;
    private String password;
    private String passportNumber;

    @Before
    public void setUp() throws Exception {
        login = "djin94";
        password = "123456";
        passportNumber = "464687123";
        String lastName = "Kabatov";
        String firstName = "Evgeny";
        String patronym = "Nikolaevich";
        String phone = "897943133";
        String role = Role.USER.getRole();

        user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setPassportNumber(passportNumber);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPatronym(patronym);
        user.setPhone(phone);
        user.setRole(role);

        userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setLastName(lastName);
        userDTO.setFirstName(firstName);
        userDTO.setPatronym(patronym);
        userDTO.setPassportNumber(passportNumber);
    }

    @Test
    @Ignore
    public void whenSaveUser_thenCryptPasswordAndSetRoleAndSaveUser(){
        User expectedUser = user;
    }
}