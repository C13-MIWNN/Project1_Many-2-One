package nl.mitw.ch13.many2one.ctrlalteat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import nl.mitw.ch13.many2one.ctrlalteat.services.RecipePdfService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Linda Munsterman
 * Purpose: represents a recipe
 **/

@Entity
public class Recipe {

    private static final int MAX_CHAR_SIZE_RECIPE_NAME = 40;

    public static final int EASY_DIFFICULTY_IN_NUMBERS = 1;
    public static final int MEDIUM_DIFFICULTY_IN_NUMBERS = 2;
    public static final int HARD_DIFFICULTY_IN_NUMBERS = 3;

    public static final int LOWER_LIMIT_MEDIUM_DIFFICULTY = 5;
    public static final int UPPER_LIMIT_MEDIUM_DIFFICULTY = 8;
    public static final int LOWER_LIMIT_HARD_DIFFICULTY = 9;

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
        int difficultyBasedOnSteps = EASY_DIFFICULTY_IN_NUMBERS;
        if (numberOfSteps >= LOWER_LIMIT_MEDIUM_DIFFICULTY && numberOfSteps <= UPPER_LIMIT_MEDIUM_DIFFICULTY) {
            difficultyBasedOnSteps = MEDIUM_DIFFICULTY_IN_NUMBERS;
        }
        else if (numberOfSteps >= LOWER_LIMIT_HARD_DIFFICULTY) {
            difficultyBasedOnSteps = HARD_DIFFICULTY_IN_NUMBERS;
        }
        return difficultyBasedOnSteps;
    }

    public String getDifficultyLevelInString(Recipe recipe) {
        int difficultyLevelInNumbers = getDifficultyInNumbers(recipe);
        String difficultyLevel = "Easy";
        if (difficultyLevelInNumbers == MEDIUM_DIFFICULTY_IN_NUMBERS)
        {
            difficultyLevel = "Medium" ;
        }
        if (difficultyLevelInNumbers == HARD_DIFFICULTY_IN_NUMBERS) {
            difficultyLevel = "Hard" ;
        }
        return difficultyLevel;
    }

    public int getNoOfPreparationMethodSteps() {
        return preparationMethodSteps.size();
    }

    public int getNoOfNonEmptyPreparationMethodSteps() {
        int count = 0;
        for (String step : preparationMethodSteps) {
            if (!step.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public byte[] makeRecipePDF() {
        return RecipePdfService.makeRecipePDF(this);
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
