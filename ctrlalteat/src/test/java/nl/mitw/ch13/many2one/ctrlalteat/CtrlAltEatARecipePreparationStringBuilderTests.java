package nl.mitw.ch13.many2one.ctrlalteat;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
public class CtrlAltEatARecipePreparationStringBuilderTests {


    private static final String[] STRING_ARRAY_WITH_MANY_STEPS = {
            "The first recipe Preparation method",
            "The second recipe Preparation method",
            "The third recipe Preparation method",
            "The fourth recipe Preparation method",
            "The fifth recipe Preparation method",
            "The sixth recipe Preparation method",
            "The seventh recipe Preparation method",
            "The eight recipe Preparation method",
            "The ninth recipe Preparation method"
    };
    private static final String EXPECTED_STRING_FOR_RECIPE_WITH_MANY_STEPS = """
            The first recipe Preparation method

            The second recipe Preparation method

            The third recipe Preparation method

            The fourth recipe Preparation method

            The fifth recipe Preparation method

            The sixth recipe Preparation method

            The seventh recipe Preparation method

            The eight recipe Preparation method

            The ninth recipe Preparation method""";

    @Test
    @DisplayName("Test result of preparation method string builder with no preparation method")
    void testPreparationMethodStringBuilderWithNoPreparationMethod() {
        Recipe recipeWithoutPreparationMethod = new Recipe();
        recipeWithoutPreparationMethod.setPreparationMethodSteps(List.of(new String[]{""}));
        String expectedString = "";

        // Act
        String actualString = recipeWithoutPreparationMethod.buildRecipePreparationMethodString();

        // Assert
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test result of preparation method string builder with a low amount of preparation method steps")
    void testPreparationMethodStringBuilderWithALowAmountOfPreparationMethodSteps() {
        Recipe recipeWithLowPreparationMethod = new Recipe();
        recipeWithLowPreparationMethod.setPreparationMethodSteps(List.of(new String[]{"1","2","3"}));
        String expectedString = "1\n\n2\n\n3";

        // Act
        String actualString = recipeWithLowPreparationMethod.buildRecipePreparationMethodString();

        // Assert
        assertEquals(expectedString, actualString);
    }


    @Test
    @DisplayName("Test result of preparation method string builder with a many preparation method steps")
    void testPreparationMethodStringBuilderWithManyPreparationMethodSteps() {
        Recipe recipeWithManyPreparationMethod = new Recipe();
        recipeWithManyPreparationMethod.setPreparationMethodSteps(List.of(STRING_ARRAY_WITH_MANY_STEPS));
        String expectedString = EXPECTED_STRING_FOR_RECIPE_WITH_MANY_STEPS;
        ;

        // Act
        String actualString = recipeWithManyPreparationMethod.buildRecipePreparationMethodString();

        // Assert
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test result of preparation method string builder with no preparation method")
    void testPreparationMethodStringBuilderWithEmptyPreparationMethod() {
        Recipe recipeWithoutPreparationMethod = new Recipe();
        recipeWithoutPreparationMethod.setPreparationMethodSteps(new ArrayList<>());
        String expectedString = "Preparation method has not been specified for this recipe.";

        // Act
        String actualString = recipeWithoutPreparationMethod.buildRecipePreparationMethodString();

        // Assert
        assertEquals(expectedString, actualString);
    }




}
