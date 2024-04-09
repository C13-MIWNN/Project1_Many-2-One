package nl.mitw.ch13.many2one.ctrlalteat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

/**
 * @author Linda Munsterman
 * Purpose: represents a recipe
 **/

@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;
    private String recipeName;
    private int preparationTimeInMinutes;
    private int servingSizeInPersons;
    private String cuisine;

    public Recipe(String recipeName, int preparationTimeInMinutes, int servingSizeInPersons, String cuisine) {
        this.recipeName = recipeName;
        this.preparationTimeInMinutes = preparationTimeInMinutes;
        this.servingSizeInPersons = servingSizeInPersons;
        this.cuisine = cuisine;
    }

    public Recipe() {
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getPreparationTimeInMinutes() {
        return preparationTimeInMinutes;
    }

    public void setPreparationTimeInMinutes(int preparationTimeInMinutes) {
        this.preparationTimeInMinutes = preparationTimeInMinutes;
    }

    public int getServingSizeInPersons() {
        return servingSizeInPersons;
    }

    public void setServingSizeInPersons(int servingSizeInPersons) {
        this.servingSizeInPersons = servingSizeInPersons;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
