package com.foxminded.airline.domain.service;

import com.foxminded.airline.dao.repository.UserRepository;
import com.foxminded.airline.domain.entity.Role;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.impl.UserServiceImpl;
import com.foxminded.airline.web.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private Authentication authentication;

    @Mock
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

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenCryptPassword_thenCryptPassword() {
        when(bCryptPasswordEncoder.encode(password)).thenReturn(cryptedPassword);

        String expectedString = cryptedPassword;
        String actualString = userService.cryptPassword(password);

        assertEquals(expectedString, actualString);
    }

    @Test
    public void whenSaveUser_thenSetRoleAndSaveUser() {
        userService.save(user);
        User actualUser = user;

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
    public void whenGetCurrentUser_thenReturnCurrentUser() {
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(login);
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        User expectedUser = user;
        User actualUser = userService.getCurrentUser().get();

        assertEquals(expectedUser, actualUser);
    }
}