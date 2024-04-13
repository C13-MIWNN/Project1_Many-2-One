package nl.mitw.ch13.many2one.ctrlalteat.controller;


import nl.mitw.ch13.many2one.ctrlalteat.enums.MeasurementUnitTypes;
import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Simon Hiemstra
 * Purpose: Fill the database with some initial values for testing purposes
 **/

@Controller
public class InitializeController {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    byte[] image;

    public InitializeController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/initialize")
    private String initializeDB() {
        Ingredient onion = makeIngredient("Onion",
                "A versatile aromatic vegetable used in various cuisines.", MeasurementUnitTypes.Item);
        Ingredient garlic = makeIngredient("Garlic",
                "Adds depth and flavor to dishes, both raw and cooked.", MeasurementUnitTypes.Item);
        Ingredient tomato = makeIngredient("Tomato",
                "Commonly used in sauces, salads, and as a base.", MeasurementUnitTypes.gram);
        Ingredient chicken = makeIngredient("Chicken",
                "A versatile protein used in countless recipes worldwide.", MeasurementUnitTypes.gram);
        Ingredient rice = makeIngredient("Rice",
                "Staple grain for side dishes, main courses, and desserts.", MeasurementUnitTypes.gram);
        Ingredient oliveOil = makeIngredient("Olive oil",
                " Healthy fat used for cooking, dressings, and flavoring.", MeasurementUnitTypes.milliliter);
        Ingredient flour = makeIngredient("Flour",
                "Essential for baking and thickening sauces and gravies.", MeasurementUnitTypes.Tablespoon);
        Ingredient salt = makeIngredient("Salt",
                "Enhances flavor and balances sweetness in dishes.", MeasurementUnitTypes.Teaspoon);
        Ingredient pepper = makeIngredient("Pepper",
                "Adds heat and depth of flavor to savory dishes.", MeasurementUnitTypes.Teaspoon);
        Ingredient egg = makeIngredient("Egg",
                "Versatile ingredient used in baking, cooking, and breakfast dishes.",
                MeasurementUnitTypes.Item);
        Ingredient pasta = makeIngredient("Pasta",
                "Versatile carbohydrate staple for countless savory and sweet dishes.",
                MeasurementUnitTypes.gram);
        Ingredient yeast = makeIngredient("Yeast",
                "Essential leavening agent for fluffy breads and delicate pastries.",
                MeasurementUnitTypes.Teaspoon);
        Ingredient water = makeIngredient("Water",
                "Universal solvent vital for hydration and culinary balance in recipes.",
                MeasurementUnitTypes.milliliter);
        Ingredient longIngredient = makeIngredient("Ingredient With a very long name, which is very long",
                "An ingredient for testing purposes which has a very long name and but also a quit long description. Definitely without any filler words, only functional ones. Ok I think this is long enough. ",
                MeasurementUnitTypes.Tablespoon);
        Ingredient shortIngredient = makeIngredient("S",
                "Short",
                MeasurementUnitTypes.milliliter);



        createImage("classic_pasta.jpg");
        Recipe classicPasta = makeRecipeWithImage("Classic Tomato and Onion Pasta",
                30,
                2,
                "Saute chopped onion and garlic in olive oil. Add diced tomatoes, salt, and pepper. Simmer. Toss with cooked pasta.",
                makeSetIngredients(onion, garlic, oliveOil, pasta, salt, pepper), image);

        createImage("roasted_chicken.jpg");
        Recipe roastedChicken = makeRecipeWithImage("Garlic and Herb Roasted Chicken",
                60,
                4,
                "Rub chicken with minced garlic, olive oil, salt, and pepper. Roast until golden brown and cooked through.",
                makeSetIngredients(chicken, garlic, oliveOil, salt, pepper), image);

        createImage("fried_rice.jpg");
        Recipe friedRice = makeRecipeWithImage("Vegetable Fried Rice",
                20,
                6,
                "Saute onion and garlic. Add cooked rice, diced tomatoes, salt, and pepper. Stir in scrambled eggs.",
                makeSetIngredients(onion, garlic, tomato, rice, oliveOil, salt, pepper, egg), image);

        createImage("Tomato_sauce.jpg");
        Recipe tomatoSauce = makeRecipeWithImage("Homemade Tomato Sauce",
                60,
                3,
                "Saute onion and garlic. Add diced tomatoes, salt, and pepper. Simmer until thickened. Blend for a smoother texture if desired.",
                makeSetIngredients(onion, garlic, tomato, oliveOil, salt, pepper), image);

        createImage("focaccia_bread.jpg");
        Recipe focacciaBread = makeRecipeWithImage("Garlic and Herb Focaccia Bread",
                90,
                4,
                "Mix flour, yeast, water, salt, and olive oil. Knead until smooth. Press dough into a pan. Top with minced garlic, olive oil, salt, and pepper. Bake until golden brown.",
                makeSetIngredients(flour, garlic, oliveOil, salt, pepper, yeast, water), image);

        Recipe shortLong = makeRecipeWithoutImage("Bread",
                120,
                4,
                "Mix. Bake.",
                makeSetIngredients(flour, shortIngredient, longIngredient, salt, yeast, water));

        return "redirect:/";
    }

    private Ingredient makeIngredient(String ingredientName, String description, MeasurementUnitTypes measurementUnitType) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientName);
        ingredient.setDescription(description);
        ingredient.setMeasurementUnit(measurementUnitType);

        ingredientRepository.save(ingredient);
        return ingredient;
    }

    private Recipe makeRecipeWithImage(String recipeName, int preparationTimeInMinutes, int servings,
                                       String preparationMethod, Set<Ingredient> ingredients, byte[] image) {
        Recipe recipe = setRecipeVariables(recipeName, preparationTimeInMinutes, servings,
                preparationMethod, ingredients);
        recipe.setImageData(image);
        recipeRepository.save(recipe);
        return recipe;
    }

    private Recipe makeRecipeWithoutImage(String recipeName, int preparationTimeInMinutes, int servings,
                                          String preparationMethod, Set<Ingredient> ingredients){
        Recipe recipe = setRecipeVariables(recipeName, preparationTimeInMinutes, servings,
                preparationMethod, ingredients);
        recipeRepository.save(recipe);
        return recipe;
    }

    private Recipe setRecipeVariables(String recipeName, int preparationTimeInMinutes, int servings,
                                      String preparationMethod, Set<Ingredient> ingredients) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setPreparationTimeInMinutes(preparationTimeInMinutes);
        recipe.setServings(servings);
        recipe.setPreparationMethod(preparationMethod);
        recipe.setIngredients(ingredients);
        return recipe;
    }

    private Set<Ingredient> makeSetIngredients(Ingredient... ingredients) {
        return new HashSet<>(Arrays.asList(ingredients));
    }

    private byte[] makeImageByteArray(String filename) throws IOException {
        File file = new File("ctrlalteat/src/main/projectDocuments/many2oneimages/" + filename);
        return Files.readAllBytes(file.toPath());
    }
    private void createImage(String filename){
        try {
            this.image = makeImageByteArray(filename);
        } catch (IOException e) {
            throw new RuntimeException("couldn't read image", e);
        }
    }
}
