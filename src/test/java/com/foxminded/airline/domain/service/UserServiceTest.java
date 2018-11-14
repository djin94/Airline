package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Role;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.dto.UserDTO;
import com.foxminded.airline.web.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
    private Authentication authentication;

    @MockBean
    private SecurityContext securityContext;

    private User user;
    private UserDTO userDTO;
    private String login;
    private String password;

    private String cryptedPassword;

    @Before
    public void setUp() throws Exception {
        login = "djin94";
        password = "123456";
        String passportNumberKabatov = "464687123";
        String lastNameKabatov = "Kabatov";
        String firstNameKabatov = "Evgeny";
        String patronymKabatov = "Nikolaevich";
        String phone = "897943133";

        user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setPassportNumber(passportNumberKabatov);
        user.setLastName(lastNameKabatov);
        user.setFirstName(firstNameKabatov);
        user.setPatronym(patronymKabatov);
        user.setPhone(phone);

        String lastNameDrozdov = "Drozdov";
        String firstNameDrozdov = "Denis";
        String patronymDrozdov = "Alekseevich";
        String passportNumberDrozdov = "456468713";
        userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setLastName(lastNameDrozdov);
        userDTO.setFirstName(firstNameDrozdov);
        userDTO.setPatronym(patronymDrozdov);
        userDTO.setPassportNumber(passportNumberDrozdov);

        cryptedPassword = new StringBuilder(password).reverse().toString();
    }

    @Test
    public void whenSaveUser_thenCryptPasswordAndSetRoleAndSaveUser() {
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn(cryptedPassword);

        userService.save(user);
        User actualUser = user;

        assertEquals(actualUser.getPassword(), cryptedPassword);
        assertEquals(actualUser.getRole(), Role.USER.getRole());
    }

    @Test
    public void whenEditPassportData_thenEditPassportData() {
        userService.editPassportData(user, userDTO);
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getPatronym(), userDTO.getPatronym());
        assertEquals(user.getPassportNumber(), userDTO.getPassportNumber());
    }

    @Test
    public void whenGetCurrentUser_thenGetCurrentUser() {
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(login);
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        User expectedUser = user;
        User actualUser = userService.getCurrentUser();

        assertEquals(expectedUser, actualUser);
    }
}