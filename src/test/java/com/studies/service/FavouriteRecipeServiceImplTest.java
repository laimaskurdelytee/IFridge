package com.studies.service;

import com.studies.IFridgeApplication;
import com.studies.model.FavouriteRecipe;
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
public class FavouriteRecipeServiceImplTest {

    @Autowired
    private FavouriteRecipeService favouriteRecipeService;

    @Test
    public void findFavouriteRecipeByUsername() throws Exception {

        String username = "user1";
        List<FavouriteRecipe> favouriteRecipeList = favouriteRecipeService.findFavouriteRecipeByUsername(username);
        assertNotEquals(0, favouriteRecipeList.size());
    }

    @Test
    public void findFavouriteRecipeByRecipeId() throws Exception {
        long id = 1;
        List<FavouriteRecipe> favouriteRecipes = favouriteRecipeService.findFavouriteRecipeByRecipeId(id);
        assertNotEquals(0, favouriteRecipes.size());
    }

    @Test
    public void saveAndRemoveFavouriteRecipe() {
        long recipeId = 2;
        String username = "user1";
        boolean exists = false;
        FavouriteRecipe favouriteRecipe = new FavouriteRecipe(username, recipeId);
        favouriteRecipeService.saveFavouriteRecipe(favouriteRecipe);

        List<FavouriteRecipe> list1 = favouriteRecipeService.findFavouriteRecipeByRecipeId(recipeId);
        for (FavouriteRecipe fr : list1) {
            if (fr.getUsername().equals(username)) {
                exists = true;
            }
        }
        assertNotEquals(0, list1.size());
        assertTrue(exists);
        exists = false;
        favouriteRecipeService.removeFavouriteRecipe(favouriteRecipe);
        list1 = favouriteRecipeService.findFavouriteRecipeByRecipeId(recipeId);
        for (FavouriteRecipe fr : list1) {
            if (fr.getUsername().equals(username)) {
                exists = true;
            }
        }
        assertFalse(exists);
        System.out.println();
    }

    @Test
    public void getFavouriteRecipes() {
        List<FavouriteRecipe> list = favouriteRecipeService.getFavouriteRecipes();
        assertNotEquals(0,list.size());
    }
}