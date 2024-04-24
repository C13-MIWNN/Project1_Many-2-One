package nl.mitw.ch13.many2one.ctrlalteat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Linda Munsterman
 * Purpose: represents a recipe
 **/

@Entity
public class Recipe {

    private static final int MAX_CHAR_SIZE_RECIPE_NAME = 40;

    @Id @GeneratedValue
    private Long recipeId;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)

    @NotEmpty(message = "Enter at least one ingredient")
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.DETACH)
    private Set<Category> categories;

    @NotEmpty(message = "Please fill out this field.") @Size(max = MAX_CHAR_SIZE_RECIPE_NAME, message = "Size must be between 0 and " + MAX_CHAR_SIZE_RECIPE_NAME)
    private String recipeName;
    private int preparationTimeInMinutes;
    private int servings;

    @ElementCollection @OrderColumn
    private List<String> preparationMethodSteps = new ArrayList<>();
    private String preparationMethod;

    private String tag;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    public Recipe(String recipeName, int preparationTimeInMinutes, int servings,
                  String tag, byte[] imageData) {
        this.recipeName = recipeName;
        this.preparationTimeInMinutes = preparationTimeInMinutes;
        this.servings = servings;
        this.tag = tag;
        this.imageData = imageData;
    }

    public Recipe() {
    }


    public String buildRecipePreparationMethodString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (preparationMethodSteps.isEmpty()) {
            return "Preparation method has not been specified for this recipe.";
        }
        stringBuilder.append(preparationMethodSteps.get(0));
        for (int i = 1; i < preparationMethodSteps.size(); i++) {
            stringBuilder.append("\n\n");
            stringBuilder.append(preparationMethodSteps.get(i));
        }
        return stringBuilder.toString();
    }

    private int getDifficultyInNumbers(Recipe recipe) {
        int numberOfSteps = recipe.getNoOfPreparationMethodSteps();
        int difficultyBasedOnSteps = 1;
        if (numberOfSteps > 3 && numberOfSteps <= 6) {
            difficultyBasedOnSteps = 2;
        }
        else if (numberOfSteps > 6) {
            difficultyBasedOnSteps = 3;
        }
        return difficultyBasedOnSteps;
    }

    public String getDifficultyLevelInString(Recipe recipe) {
        int difficultyLevelInNumbers = getDifficultyInNumbers(recipe);
        String difficultyLevel = "Easy";
        if (difficultyLevelInNumbers == 2)
        {
            difficultyLevel = "Medium" ;
        }
        if (difficultyLevelInNumbers == 3) {
            difficultyLevel = "Hard" ;
        }
        return difficultyLevel;
    }

    public int getNoOfPreparationMethodSteps() {
        return preparationMethodSteps.size();
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

    public List<String> getPreparationMethodSteps() {
        return preparationMethodSteps;
    }

    public void setPreparationMethodSteps(List<String> preparationMethodSteps) {
        this.preparationMethodSteps = preparationMethodSteps;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

}
