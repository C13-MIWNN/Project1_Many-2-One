package nl.mitw.ch13.many2one.ctrlalteat.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Simon Hiemstra
 * Purpose: Reprisents an ingredient that can be saved in the database and used in recipes.
 **/
@Entity
public class Ingredient {
    @Id @GeneratedValue
    private Long ingredientId;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<RecipeIngredient> recipes = new ArrayList<>();

    private String name;
    private String description;
    private String kCal;
    private double protein;
    private double fats;
    private double carbs;


    public Ingredient(String name, Long ingredientId, String description, String kCal, double protein, double fats, double carbs) {
        this.name = name;
        this.ingredientId = ingredientId;
        this.description = description;
        this.kCal = kCal;
        this.protein = protein;
        this.fats = fats;
        this.carbs = carbs;
    }

    public Ingredient() {
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public List<RecipeIngredient> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeIngredient> recipes) {
        this.recipes = recipes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getkCal() {
        return kCal;
    }

    public void setkCal(String kCal) {
        this.kCal = kCal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
