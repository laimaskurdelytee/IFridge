package com.studies.service;

import com.studies.IFridgeApplication;
import com.studies.model.Ingredient;
import com.studies.model.Measure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.studies.model.Measure.ml;
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
public class IngredientServiceImplTest {

    @Autowired
    private IngredientService ingredientService;

    @Test
    public void findIngredientById() throws Exception {
        long id = 1;
        Ingredient ingredient = ingredientService.findIngredientById(id);
        assertNotNull(ingredient);
    }

    @Test
    public void saveAndRemoveIngredient() throws Exception {
        String name = "pienas";
        Measure mu = ml;
        Ingredient ingredient = new Ingredient(name,mu);
        ingredientService.saveIngredient(ingredient);
        Ingredient ingredient2 = ingredientService.findIngredientById(ingredient.getId());
        assertNotNull(ingredient2);
        System.out.println("ALIO "+ingredient.getId());
        ingredientService.removeIngredient(ingredient2);
        assertNull(ingredientService.findIngredientById(ingredient2.getId()));
    }

    @Test
    public void getIngredients() throws Exception {
        List<Ingredient> list = ingredientService.getIngredients();
        assertNotEquals(0, list.size());
    }

}