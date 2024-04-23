package nl.mitw.ch13.many2one.ctrlalteat;
import nl.mitw.ch13.many2one.ctrlalteat.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author Arjan Cnossen
 * Purpose: Testing the new function to add a difficulty to every recipe, based on the number of steps and ingredients.
 **/

public class CtrlAltEatRecipeDifficultyTest {

    @Test
    @DisplayName("Difficulty for recipes with a low number of ingredients and steps")
    void DifficultyForRecipesWithLowNumberOfIngredientsAndSteps() {

        String recipeName = "Chicken soup";
        String[] preparationMethodSteps = {"Saute onion and garlic.",
                "Add cooked rice, diced tomatoes, salt, and pepper. ",
                "Stir in scrambled eggs."};
        String[] ingredients = {"Saute onion and garlic.",
                "Add cooked rice, diced tomatoes, salt, and pepper. ",
                "Stir in scrambled eggs."};

        Recipe recipe = getRecipe(recipeName, preparationMethodSteps, ingredients);

        int number = ingredients.length;

        // something with assertEquals? Expected?

    }

    public int difficultyBasedOnSteps(Recipe recipe) {
        int numberOfSteps = recipe.getNoOfPreparationMethodSteps();
        return numberOfSteps;

    }

    private static Recipe getRecipe(String recipeName, String[] preparationMethodSteps, String[] ingredients) {

        RecipeIngredient recipeIngredient = new RecipeIngredient();

        List<RecipeIngredient> recipeIngredientList = new ArrayList<>();
        recipeIngredientList.add(recipeIngredient);

        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setPreparationMethodSteps(List.of(preparationMethodSteps));
        recipe.setIngredients(recipeIngredientList);

        return recipe;
    }

}
