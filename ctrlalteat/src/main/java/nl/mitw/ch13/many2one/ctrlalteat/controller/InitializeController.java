package nl.mitw.ch13.many2one.ctrlalteat.controller;


import nl.mitw.ch13.many2one.ctrlalteat.enums.MeasurementUnitTypes;
import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


        Set<Ingredient> cpIngredients = new HashSet<>(Arrays.asList(onion, garlic, oliveOil, pasta, salt, pepper));
        Recipe classicPasta = makeRecipe("Classic Tomato and Onion Pasta",
                30,
                2,
                "Saute chopped onion and garlic in olive oil. Add diced tomatoes, salt, and pepper. Simmer. Toss with cooked pasta.",
                cpIngredients);

        Set<Ingredient> rcIngredients = new HashSet<>(Arrays.asList(chicken, garlic, oliveOil, salt, pepper));
        Recipe roastedChicken = makeRecipe("Garlic and Herb Roasted Chicken",
                60,
                4,
                "Rub chicken with minced garlic, olive oil, salt, and pepper. Roast until golden brown and cooked through.",
                rcIngredients);

        Set<Ingredient> vcIngredients = new HashSet<>(Arrays.asList(onion, garlic, tomato, rice, oliveOil, salt, pepper, egg));
        Recipe friedRice = makeRecipe("Vegetable Fried Rice",
                20,
                6,
                "Saute onion and garlic. Add cooked rice, diced tomatoes, salt, and pepper. Stir in scrambled eggs.",
                vcIngredients);

        Set<Ingredient> tsIngredients = new HashSet<>(Arrays.asList(onion, garlic, tomato, oliveOil, salt, pepper));
        Recipe tomatoSauce = makeRecipe("Homemade Tomato Sauce",
                60,
                3,
                "Saute onion and garlic. Add diced tomatoes, salt, and pepper. Simmer until thickened. Blend for a smoother texture if desired.",
                tsIngredients);

        Set<Ingredient> fbIngredients = new HashSet<>(Arrays.asList(flour, garlic, oliveOil, salt, pepper, yeast, water));
        Recipe focacciaBread = makeRecipe("Garlic and Herb Focaccia Bread",
                90,
                4,
                "Mix flour, yeast, water, salt, and olive oil. Knead until smooth. Press dough into a pan. Top with minced garlic, olive oil, salt, and pepper. Bake until golden brown.",
                fbIngredients);
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

    private Recipe makeRecipe(String recipeName, int preparationTimeInMinutes, int servings,
                              String preparationMethod, Set<Ingredient> ingredients) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setPreparationTimeInMinutes(preparationTimeInMinutes);
        recipe.setServings(servings);
        recipe.setPreparationMethod(preparationMethod);
        recipe.setIngredients(ingredients);

        recipeRepository.save(recipe);
        return recipe;
    }

}
