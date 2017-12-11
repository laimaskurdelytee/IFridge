package com.studies.service;

import com.studies.IFridgeApplication;
import com.studies.model.RecipeIngredient;
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
public class RecipeIngredientServiceImplTest {

    @Autowired
    private RecipeIngredientService recipeIngredientService;

    @Test
    public void findRecipeIngredientsByRecipeId() throws Exception {
        long id = 1;
        List<RecipeIngredient> list = recipeIngredientService.findRecipeIngredientsByRecipeId(id);
        assertNotEquals(0,list.size());
    }

    @Test
    public void findRecipeIngredientsByIngredientId() throws Exception {
        long id = 1;
        List<RecipeIngredient> list = recipeIngredientService.findRecipeIngredientsByIngredientId(id);
        assertNotEquals(0,list.size());
    }

    @Test
    public void saveAndRemoveRecipeIngredient() throws Exception {
        long recipeId = 2;
        long ingredientId = 9;
        double amount = 100;
        boolean exists = false;
        RecipeIngredient recipeIngredient = new RecipeIngredient(recipeId,ingredientId,amount);
        recipeIngredientService.saveRecipeIngredient(recipeIngredient);
        List<RecipeIngredient> recipeIngredientList = recipeIngredientService.findRecipeIngredientsByIngredientId(recipeIngredient.getIngredientId());
        for(RecipeIngredient ri: recipeIngredientList) {
            if(ri.getRecipeId().equals(recipeIngredient.getRecipeId())) {
                exists = true;
            }
        }
        assertNotEquals(0, recipeIngredientList.size());
        assertTrue(exists);
        exists = false;
        recipeIngredientService.removeRecipeIngredient(recipeIngredient);
        recipeIngredientList = recipeIngredientService.findRecipeIngredientsByIngredientId(recipeIngredient.getIngredientId());
        for(RecipeIngredient ri: recipeIngredientList) {
            if(ri.getRecipeId().equals(recipeIngredient.getRecipeId())) {
                exists = true;
            }
        }
        assertFalse(exists);
    }

}