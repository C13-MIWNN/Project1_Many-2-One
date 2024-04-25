package nl.mitw.ch13.many2one.ctrlalteat;
import nl.mitw.ch13.many2one.ctrlalteat.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Arjan Cnossen
 * Purpose: Testing the new function to add a difficulty to every recipe, based on the number of steps.
 * Testing the edge cases of the related functions in the Recipe model.
 **/

public class CtrlAltEatRecipeDifficultyTest {

    @Test
    @DisplayName("Difficulty for recipes without any steps")
    void DifficultyForRecipesWithJustOneStep () {

        String[] preparationMethodSteps = new String[0];
        Recipe recipe = getRecipe("0 steps recipe", preparationMethodSteps);

        String numberOfSteps = recipe.getDifficultyLevelInString(recipe);
        String expectedNumberOfSteps = "Easy";

        assertEquals(expectedNumberOfSteps, numberOfSteps);
    }

    @Test
    @DisplayName("Difficulty for recipes with 4 steps (the most amount of steps for easy)")
    void DifficultyForRecipesWith4Steps () {

        String[] preparationMethodSteps = new String[4];
        for (int i = 0; i < 4 ; i++) {
            preparationMethodSteps[i] = "Step";
        }

        Recipe recipe = getRecipe("4 steps recipe", preparationMethodSteps);

        String numberOfSteps = recipe.getDifficultyLevelInString(recipe);
        String expectedNumberOfSteps = "Easy";

        assertEquals(expectedNumberOfSteps, numberOfSteps);
    }

    @Test
    @DisplayName("Difficulty for recipes with 5 steps (the least amount of steps for medium)")
    void DifficultyForRecipesWith5Steps () {

        String[] preparationMethodSteps = new String[5];
        for (int i = 0; i < 5 ; i++) {
            preparationMethodSteps[i] = "Step";
        }

        Recipe recipe = getRecipe("5 steps recipe", preparationMethodSteps);

        String numberOfSteps = recipe.getDifficultyLevelInString(recipe);
        String expectedNumberOfSteps = "Medium";

        assertEquals(expectedNumberOfSteps, numberOfSteps);
    }

    @Test
    @DisplayName("Difficulty for recipes with 8 steps (the most amount of steps for medium)")
    void DifficultyForRecipesWith8Steps () {

        String[] preparationMethodSteps = new String[8];
        for (int i = 0; i < 8 ; i++) {
            preparationMethodSteps[i] = "Step";
        }

        Recipe recipe = getRecipe("8 steps recipe", preparationMethodSteps);

        String numberOfSteps = recipe.getDifficultyLevelInString(recipe);
        String expectedNumberOfSteps = "Medium";

        assertEquals(expectedNumberOfSteps, numberOfSteps);
    }

    @Test
    @DisplayName("Difficulty for recipes with 9 steps (the least amount of steps for hard)")
    void DifficultyForRecipesWith9Steps () {

        String[] preparationMethodSteps = new String[9];
        for (int i = 0; i < 9 ; i++) {
            preparationMethodSteps[i] = "Step";
        }

        Recipe recipe = getRecipe("9 steps recipe", preparationMethodSteps);

        String numberOfSteps = recipe.getDifficultyLevelInString(recipe);
        String expectedNumberOfSteps = "Hard";

        assertEquals(expectedNumberOfSteps, numberOfSteps);
    }

    @Test
    @DisplayName("Difficulty for a recipe with a high number of steps)")
    void DifficultyForRecipesWithHighNumberOfSteps () {

        String[] preparationMethodSteps = new String[20];
        for (int i = 0; i < 20 ; i++) {
            preparationMethodSteps[i] = "Step";
        }

        Recipe recipe = getRecipe("20 steps recipe", preparationMethodSteps);

        String numberOfSteps = recipe.getDifficultyLevelInString(recipe);
        String expectedNumberOfSteps = "Hard";

        assertEquals(expectedNumberOfSteps, numberOfSteps);
    }


    private static Recipe getRecipe(String recipeName, String[] preparationMethodSteps){
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setPreparationMethodSteps(Arrays.asList(preparationMethodSteps));

        return recipe;
    }
}
