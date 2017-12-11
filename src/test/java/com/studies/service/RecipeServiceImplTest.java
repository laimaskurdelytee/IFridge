package com.studies.service;

import com.studies.IFridgeApplication;
import com.studies.model.Category;
import com.studies.model.Recipe;
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
public class RecipeServiceImplTest {

    @Autowired
    private RecipeService recipeService;

    @Test
    public void findRecipeById() throws Exception {
        long id = 1;
        Recipe recipe = recipeService.findRecipeById(id);
        assertNotNull(recipe);
    }

    @Test
    public void saveAndRemoveRecipe() throws Exception {
        String name = "name";
        String description = "description";
        Category category = Category.desertas;
        double cookingTime = 30;
        Date date = new Date();
        int favouriteCount = 0;
        int numberOfServings = 2;
        byte[] image = null;
        int viewCount = 0;
        int unseenDays = 0;
        Recipe recipe = new Recipe(name,description,cookingTime,category,numberOfServings,date,image,viewCount,favouriteCount,unseenDays);
        recipeService.saveRecipe(recipe);
        Recipe newRecipe = recipeService.findRecipeById(recipe.getId());
        assertNotNull(newRecipe);
        recipeService.removeRecipe(newRecipe);
        assertNull(recipeService.findRecipeById(newRecipe.getId()));
    }

    @Test
    public void updateRecipe() throws Exception {
        long id = 1;
        boolean updated = false;
        Recipe recipe = recipeService.findRecipeById(id);
        assertNotNull(recipe);
        String name = recipe.getName();
        String updatedName = "bum";
        recipe.setName(updatedName);
        recipeService.updateRecipe(recipe);
        if (!recipe.getName().equals(name)) {
            updated = true;
        }
        assertTrue(updated);
        updated = false;
        recipe.setName(name);
        recipeService.updateRecipe(recipe);
        if (recipe.getName().equals(name)) {
            updated = true;
        }
        assertTrue(updated);
    }

    @Test
    public void getRecipes() throws Exception {
        List<Recipe> recipeList = recipeService.getRecipes();
        assertNotEquals(0,recipeList.size());
    }

}