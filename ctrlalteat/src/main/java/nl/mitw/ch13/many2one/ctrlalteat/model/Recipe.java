package nl.mitw.ch13.many2one.ctrlalteat.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Linda Munsterman
 * Purpose: represents a recipe
 **/

@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;
    @ManyToMany(cascade = CascadeType.DETACH)
    private Set<Ingredient> ingredients;

    private String recipeName;
    private int preparationTimeInMinutes;
<<<<<<< HEAD
    private int servings;
||||||| cdec74b
    private int servingSizeInPersons;
=======
    private int servingSizeInPersons;
    @Column(columnDefinition="TEXT")
>>>>>>> 10efaf015f7ac4ff6a4901ffed7d469ccbc50f70
    private String preparationMethod;
    private String tag;


    public Recipe(String recipeName, int preparationTimeInMinutes, int servings,
                  String preparationMethod, String tag) {
        this.recipeName = recipeName;
        this.preparationTimeInMinutes = preparationTimeInMinutes;
        this.servings = servings;
        this.preparationMethod = preparationMethod;
        this.tag = tag;
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

    public int getServings() {
        return servings;
    }

    public void setServings(int servingSizeInPersons) {
        this.servings = servingSizeInPersons;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
