package com.foxminded.airline.domain.service.integration;

import com.foxminded.airline.domain.entity.Role;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.web.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;

    private User user;
    private UserDTO userDTO;
    private String login;
    private String password;

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
        user.setId((long) 4);
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
    }

    @Test
    public void whenSaveUser_thenSetRoleAndSaveUser() {
        userService.save(user);
        User actualUser = user;

        assertEquals(actualUser.getRole(), Role.USER.getRole());
    }

    @Test
    @WithMockUser(username = "djin94")
    public void whenGetCurrentUser_thenReturnCurrentUser() {
        User expectedUser = user;
        User actualUser = userService.getCurrentUser().get();

        assertEquals(expectedUser, actualUser);
    }
}