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

    public Ingredient(Long ingredientId, String name, String description) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.description = description;
    }

    public Ingredient() {
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
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

    @Override
    public String toString() {
        return this.name;
    }

    public List<RecipeIngredient> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeIngredient> recipes) {
        this.recipes = recipes;
    }
}
