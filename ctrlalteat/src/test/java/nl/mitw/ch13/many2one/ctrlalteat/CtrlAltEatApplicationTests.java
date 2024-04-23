package nl.mitw.ch13.many2one.ctrlalteat;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CtrlAltEatApplicationTests {

	@Test
	void contextLoads() {
	}

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

}
