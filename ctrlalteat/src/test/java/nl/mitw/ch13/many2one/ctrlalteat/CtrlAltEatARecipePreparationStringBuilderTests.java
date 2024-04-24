package nl.mitw.ch13.many2one.ctrlalteat;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
public class CtrlAltEatARecipePreparationStringBuilderTests {


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
        Recipe recipeWithManyPreparationMethod = getRecipeWithManySteps();
        String expectedString = getExpectedStringForMethodWithManySteps();
        ;

        // Act
        String actualString = recipeWithManyPreparationMethod.buildRecipePreparationMethodString();

        // Assert
        assertEquals(expectedString, actualString);
    }


    private static Recipe getRecipeWithManySteps() {
        Recipe recipeWithManyPreparationMethod = new Recipe();
        recipeWithManyPreparationMethod.setPreparationMethodSteps(List.of(new String[]{
                "The first recipe Preparation method",
                "The second recipe Preparation method",
                "The third recipe Preparation method",
                "The fourth recipe Preparation method",
                "The fifth recipe Preparation method",
                "The sixth recipe Preparation method",
                "The seventh recipe Preparation method",
                "The eight recipe Preparation method",
                "The ninth recipe Preparation method"
        }));
        return recipeWithManyPreparationMethod;
    }
    private static String getExpectedStringForMethodWithManySteps() {
        return """
                The first recipe Preparation method

                The second recipe Preparation method

                The third recipe Preparation method

                The fourth recipe Preparation method

                The fifth recipe Preparation method

                The sixth recipe Preparation method

                The seventh recipe Preparation method

                The eight recipe Preparation method

                The ninth recipe Preparation method""";
    }


}
