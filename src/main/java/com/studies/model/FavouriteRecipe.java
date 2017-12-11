package com.studies.model;

import javax.persistence.*;

@Entity
@IdClass(value = FavouriteRecipetKey.class)
@Table(name = "favouriteRecipe")
public class FavouriteRecipe {

    @Id
    @Column(name = "username")
    private String username;

    @Id
    @Column(name = "recipeId")
    private Long recipeId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public FavouriteRecipe(String username, Long recipeId) {
        this.username = username;
        this.recipeId = recipeId;
    }

    public FavouriteRecipe() {}
}