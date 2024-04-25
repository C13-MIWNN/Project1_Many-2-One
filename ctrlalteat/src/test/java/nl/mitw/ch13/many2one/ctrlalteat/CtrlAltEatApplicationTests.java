package nl.mitw.ch13.many2one.ctrlalteat;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CtrlAltEatApplicationTests {

	@Test
	@DisplayName("Number of preparation method steps for recipes without steps")
	void recipesWithoutSteps() {
		// Arrange
		Recipe recipe = new Recipe();
		int expectedSteps = 0;

		// Act
		int actualSteps = recipe.getNoOfPreparationMethodSteps();

		// Assert
		assertEquals(expectedSteps, actualSteps);
	}

	@Test
	@DisplayName("Number of preparation method steps for recipes with several steps")
	void recipesWithSeveralSteps() {
		// Arrange
		Recipe recipe = new Recipe();
		recipe.getPreparationMethodSteps().add("Step 1: Prepare ingredients");
		recipe.getPreparationMethodSteps().add("Step 2: Cook ingredients");
		recipe.getPreparationMethodSteps().add("Step 3: Serve your meal");
		int expectedSteps = 3;

		// Act
		int actualSteps = recipe.getNoOfPreparationMethodSteps();

		// Assert
		assertEquals(expectedSteps, actualSteps);
	}

	@Test
	@DisplayName("Number of preparation method steps for recipes with empty steps")
	void recipesWithEmptySteps() {
		Recipe recipe = new Recipe();
		recipe.getPreparationMethodSteps().add(""); // Empty step
		recipe.getPreparationMethodSteps().add("Step 1: Prepare ingredients");
		recipe.getPreparationMethodSteps().add(""); // Empty step
		recipe.getPreparationMethodSteps().add("Step 2: Cook ingredients");
		int expectedSteps = 2; // there are 2 non-empty steps

		// Act
		int actualSteps = recipe.getNoOfNonEmptyPreparationMethodSteps();

		// Assert
		assertEquals(expectedSteps, actualSteps);
	}


}
