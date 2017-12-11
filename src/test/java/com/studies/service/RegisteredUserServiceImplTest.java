package com.studies.service;

import com.studies.IFridgeApplication;
import com.studies.model.RegisteredUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Laima on 10/12/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = IFridgeApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class RegisteredUserServiceImplTest {

    @Autowired
    private RegisteredUserService registeredUserService;

    @Test
    public void findUserByUsername() throws Exception {
        String username = "user1";
        RegisteredUser registeredUser = registeredUserService.findUserByUsername(username);
        assertNotNull(registeredUser);
    }

    @Test
    public void saveAndRemoveUser() throws Exception {
        String username = "user2";
        String firstName = "firstName";
        String lastName = "lastName";
        String password = "password";
        String email = "email@gmail.com";
        int active = 0;
        int userLevel = 0;
        Date loginDate = new Date();
        String token = "0";
        String validTo = "0";
        RegisteredUser registeredUser = new RegisteredUser(firstName,lastName,password,username,email,
                active,userLevel,loginDate,token,validTo);
        registeredUserService.saveUser(registeredUser);
        RegisteredUser newUser = registeredUserService.findUserByUsername(registeredUser.getUsername());
        assertNotNull(newUser);
        registeredUserService.removeUser(newUser);
        RegisteredUser removedUser = registeredUserService.findUserByUsername(registeredUser.getUsername());
        assertNull(removedUser);
    }

    @Test
    public void updateUser() throws Exception {
        String username = "user1";
        boolean updated = false;
        RegisteredUser user = registeredUserService.findUserByUsername(username);
        assertNotNull(user);
        String name = user.getFirstName();
        String updatedName = "firstname";
        user.setFirstName(updatedName);
        registeredUserService.updateUser(user);
        if (!user.getFirstName().equals(name)) {
            updated = true;
        }
        assertTrue(updated);
        updated = false;
        user.setFirstName(name);
        registeredUserService.updateUser(user);
        if (user.getFirstName().equals(name)) {
            updated = true;
        }
        assertTrue(updated);
    }

    @Test
    public void getUsers() throws Exception {
        List<RegisteredUser> list = registeredUserService.getUsers();
        assertNotEquals(0, list.size());
    }

}