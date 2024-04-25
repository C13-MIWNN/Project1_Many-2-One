package nl.mitw.ch13.many2one.ctrlalteat;
import nl.mitw.ch13.many2one.ctrlalteat.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Arjan Cnossen
 * Purpose: Testing the new function to add a difficulty to every recipe, based on the number of steps.
 **/

public class CtrlAltEatRecipeDifficultyTest {

    @Test
    @DisplayName("Difficulty for recipes without any steps")
    void DifficultyForRecipesWithJustOneStep () {

        String[] preparationMethodSteps = {};
        Recipe recipe = getRecipe("Nothingness", preparationMethodSteps);

        String numberOfSteps = recipe.getDifficultyLevelInString(recipe);
        String expectedNumberOfSteps = "Easy";

        assertEquals(expectedNumberOfSteps, numberOfSteps);
    }

    @Test
    @DisplayName("Difficulty for recipes with 3 steps (one away from medium)")
    void DifficultForRecipesWithThreeSteps () {

        // use a forloop from now on
        String[] preparationMethodSteps = {};

        Recipe recipe = getRecipe("Nothingness", preparationMethodSteps);

        String numberOfSteps = recipe.getDifficultyLevelInString(recipe);
        String expectedNumberOfSteps = "Easy";

        assertEquals(expectedNumberOfSteps, numberOfSteps);
    }
//
//    @Test
//    DisplayName("Difficulty for recipes with 4 steps")
//    void DifficultyForRecipesWith4Steps () {
//
//        String[] preparationMethodSteps = {}
//    }
    // randgevallen testen
    // in recipe zetten, die ik ga testen als public

    private static Recipe getRecipe(String recipeName, String[] preparationMethodSteps){
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setPreparationMethodSteps(Arrays.asList(preparationMethodSteps));

        return recipe;
    }
}
