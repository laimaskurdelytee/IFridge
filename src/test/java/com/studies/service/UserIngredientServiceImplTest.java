package com.studies.service;

import com.studies.IFridgeApplication;
import com.studies.model.UserIngredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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
public class UserIngredientServiceImplTest {

    @Autowired
    private UserIngredientService userIngredientService;

    @Test
    public void findUserIngredientByUsername() throws Exception {
        String username = "user1";
        List<UserIngredient> list = userIngredientService.findUserIngredientByUsername(username);
        assertNotEquals(0, list.size());
    }

    @Test
    public void findUserIngredientByIngredientId() throws Exception {
        long id = 1;
        List<UserIngredient> list = userIngredientService.findUserIngredientByIngredientId(id);
        assertNotEquals(0, list.size());
    }

    @Test
    public void saveAndRemoveUserIngredient() throws Exception {
        String username = "user1";
        long id = 5;
        double amount = 111;
        boolean exists = false;
        UserIngredient userIngredient = new UserIngredient(id,username,amount);
        userIngredientService.saveUserIngredient(userIngredient);
        List<UserIngredient> list = userIngredientService.findUserIngredientByUsername(userIngredient.getUsername());
        for (UserIngredient ui : list) {
            if (ui.getIngredientId() == userIngredient.getIngredientId()) {
                exists = true;
            }
        }
        assertNotEquals(0,list.size());
        assertTrue(exists);
        exists = false;
        userIngredientService.removeUserIngredient(userIngredient);
        List<UserIngredient> list2 = userIngredientService.findUserIngredientByUsername(userIngredient.getUsername());
        for (UserIngredient ui : list2) {
            if (ui.getIngredientId() == userIngredient.getIngredientId()) {
                exists = true;
            }
        }
        assertFalse(exists);
    }

    @Test
    public void getUserIngredient() throws Exception {
        List<UserIngredient> list = userIngredientService.getUserIngredient();
        assertNotEquals(0, list.size());
    }

}