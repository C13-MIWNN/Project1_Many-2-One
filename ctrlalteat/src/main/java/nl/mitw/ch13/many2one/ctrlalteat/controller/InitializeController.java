package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.enums.MeasurementUnitTypes;
import nl.mitw.ch13.many2one.ctrlalteat.model.Category;
import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.model.RecipeIngredient;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.CategoryRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeIngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @author Simon Hiemstra
 * Purpose: Fill the database with some initial values for testing purposes
 **/

@Controller
public class InitializeController {
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    byte[] image;

    public InitializeController(IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository, RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/initialize")
    private String initializeDB() {
        Ingredient onion = makeIngredient("Onion",
                "A versatile aromatic vegetable used in various cuisines.");
        Ingredient garlic = makeIngredient("Garlic",
                "Adds depth and flavor to dishes, both raw and cooked.");
        Ingredient tomato = makeIngredient("Tomato",
                "Commonly used in sauces, salads, and as a base.");
        Ingredient chicken = makeIngredient("Chicken",
                "A versatile protein used in countless recipes worldwide.");
        Ingredient rice = makeIngredient("Rice",
                "Staple grain for side dishes, main courses, and desserts.");
        Ingredient oliveOil = makeIngredient("Olive oil",
                " Healthy fat used for cooking, dressings, and flavoring.");
        Ingredient flour = makeIngredient("Flour",
                "Essential for baking and thickening sauces and gravies.");
        Ingredient salt = makeIngredient("Salt",
                "Enhances flavor and balances sweetness in dishes.");
        Ingredient pepper = makeIngredient("Pepper",
                "Adds heat and depth of flavor to savory dishes.");
        Ingredient egg = makeIngredient("Egg",
                "Versatile ingredient used in baking, cooking, and breakfast dishes.");
        Ingredient pasta = makeIngredient("Pasta",
                "Versatile carbohydrate staple for countless savory and sweet dishes.");
        Ingredient yeast = makeIngredient("Yeast",
                "Essential leavening agent for fluffy breads and delicate pastries.");
        Ingredient water = makeIngredient("Water",
                "Universal solvent vital for hydration and culinary balance in recipes.");
        Ingredient longIngredient = makeIngredient("Ingredient With a very long name, which is very long",
                "An ingredient for testing purposes which has a very long name and but also a quit long description. Definitely without any filler words, only functional ones. Ok I think this is long enough. ");
        Ingredient shortIngredient = makeIngredient("S",
                "Short");
        Ingredient bread = makeIngredient("Bread",
                "Staple carbohydrate made from flour, water, yeast, and salt.");

        Category breakfastCat = makeCategory("Breakfast");
        Category lunchCat = makeCategory("Lunch");
        Category dinnerCat = makeCategory("Dinner");
        Category appetizerCat = makeCategory("Appetizer");
        Category mainCourseCat = makeCategory("Main course");
        Category dessertCat = makeCategory("Dessert");
        Category veganCat = makeCategory("Vegan");
        Category pastaCat = makeCategory("Pasta");
        Category chickenCat = makeCategory("Chicken");
        Category stirFryCat = makeCategory("Stir fry");


        createImage("classic_pasta.jpg");
        Recipe classicPasta = makeRecipeWithImage("Classic Tomato and Onion Pasta",
                30,
                2,
                new String[]{"Saute chopped onion and garlic in olive oil.",
                        "Add diced tomatoes, salt, and pepper.",
                        "Simmer. Toss with cooked pasta."},
                makeListIngredients(onion, garlic, oliveOil, pasta, salt, pepper),
                makeListUnits(MeasurementUnitTypes.Item, MeasurementUnitTypes.Item, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.gram, MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon),
                makeSetCategories(pastaCat, dinnerCat, mainCourseCat, veganCat), image);

        createImage("roasted_chicken.jpg");
        Recipe roastedChicken = makeRecipeWithImage("Garlic and Herb Roasted Chicken",
                60,
                4,
                new String[]{"Rub chicken with minced garlic, olive oil, salt, and pepper.",
                        "Roast until golden brown and cooked through."},
                makeListIngredients(chicken, garlic, oliveOil, salt, pepper),
                makeListUnits(MeasurementUnitTypes.gram, MeasurementUnitTypes.Item, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon),
                makeSetCategories(chickenCat, dinnerCat), image);

        createImage("fried_rice.jpg");
        Recipe friedRice = makeRecipeWithImage("Vegetable Fried Rice",
                20,
                6,
                new String[]{"Saute onion and garlic.",
                        "Add cooked rice, diced tomatoes, salt, and pepper. ",
                        "Stir in scrambled eggs."},
                makeListIngredients(onion, garlic, tomato, rice, oliveOil, salt, pepper, egg),
                makeListUnits(MeasurementUnitTypes.Item, MeasurementUnitTypes.Item, MeasurementUnitTypes.gram,
                        MeasurementUnitTypes.gram, MeasurementUnitTypes.milliliter, MeasurementUnitTypes.Teaspoon,
                        MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Item),
                makeSetCategories(stirFryCat, dinnerCat, mainCourseCat), image);

        createImage("Tomato_sauce.jpg");
        Recipe tomatoSauce = makeRecipeWithImage("Homemade Tomato Sauce",
                60,
                3,
                new String[]{"Saute onion and garlic.",
                        "Add diced tomatoes, salt, and pepper. Simmer until thickened.",
                        "Blend for a smoother texture if desired."},
                makeListIngredients(onion, garlic, tomato, oliveOil, salt, pepper),
                makeListUnits(MeasurementUnitTypes.Item, MeasurementUnitTypes.Item, MeasurementUnitTypes.gram,
                        MeasurementUnitTypes.milliliter, MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon),
                makeSetCategories(pastaCat, dinnerCat, lunchCat), image);

        createImage("focaccia_bread.jpg");
        Recipe focacciaBread = makeRecipeWithImage("Garlic and Herb Focaccia Bread",
                90,
                4,
                new String[]{"Mix flour, yeast, water, salt, and olive oil.",
                        "Knead until smooth.",
                        "Press dough into a pan. Top with minced garlic, olive oil, salt, and pepper.",
                        "Bake until golden brown."},
                makeListIngredients(flour, garlic, oliveOil, salt, pepper, yeast, water),
                makeListUnits(MeasurementUnitTypes.gram, MeasurementUnitTypes.Item, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon,
                        MeasurementUnitTypes.milliliter),
                makeSetCategories(breakfastCat, lunchCat, appetizerCat, veganCat), image);
        createImage("bread.jpg");
        Recipe shortLong = makeRecipeWithImage("Bread",
                120,
                4,
                new String[]{"Mix.",
                        "Bake"},
                makeListIngredients(flour, shortIngredient, longIngredient, salt, yeast, water,
                        flour, shortIngredient, longIngredient, salt, yeast, water,
                        flour, shortIngredient, longIngredient, salt, yeast, water,
                        flour, shortIngredient, longIngredient, salt, yeast, water),
                makeListUnits(MeasurementUnitTypes.gram, MeasurementUnitTypes.Item, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.gram, MeasurementUnitTypes.Item, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.gram, MeasurementUnitTypes.Item, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.gram, MeasurementUnitTypes.Item, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.milliliter),
                makeSetCategories(breakfastCat, lunchCat, dinnerCat, appetizerCat, mainCourseCat, dessertCat), image);
        createImage("bruschetta.jpg");
        Recipe bruschetta = makeRecipeWithImage("Sautéed Garlic Tomato Bruschetta",
                30,
                4,
                new String[]{
                        """
Prepare Garlic: Peel and finely mince the garlic cloves. Set aside.""",
                        """
Dice Tomatoes: Wash and dice the tomatoes into small, uniform pieces. Set aside.""",
                        """
Sauté Garlic: In a skillet, heat a tablespoon of olive oil over medium heat. \
Add the minced garlic and sauté until fragrant and lightly golden, being careful not to \
burn it.""",
                        """
Add Tomatoes: Once the garlic is fragrant, add the diced tomatoes to the skillet. \
Season with salt and pepper to taste.""",
                        """
Simmer Mixture: Allow the garlic and tomatoes to simmer together for about 5-7 minutes, \
or until the tomatoes soften and release their juices, creating a flavorful sauce.""",
                        """
Prepare Bread: While the tomato mixture simmers, slice the bread into thin slices. \
Optionally, you can toast the bread slices for added crunch.""",
                        """
Assemble Bruschetta: Spoon the garlic tomato mixture generously onto each slice of bread, \
ensuring that the flavors are evenly distributed.""",
                        """
Serve: Arrange the assembled bruschetta on a serving platter and garnish with a drizzle of  \
olive oil and fresh herbs if desired. Serve immediately and enjoy the vibrant flavors of  \
this classic Italian appetizer."""
                },
                makeListIngredients(garlic, tomato, oliveOil, salt, pepper, bread),
                makeListUnits(MeasurementUnitTypes.Item, MeasurementUnitTypes.gram, MeasurementUnitTypes.milliliter,
                        MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.Teaspoon, MeasurementUnitTypes.gram),
                makeSetCategories(appetizerCat, lunchCat, veganCat), image);

        return "redirect:/";
    }

    private Ingredient makeIngredient(String ingredientName, String description) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientName);
        ingredient.setDescription(description);

        ingredientRepository.save(ingredient);
        return ingredient;
    }

    private Category makeCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
        return category;
    }

    private Recipe makeRecipeWithImage(String recipeName, int preparationTimeInMinutes, int servings,
                                       String[] preparationMethodSteps, List<RecipeIngredient> ingredients,
                                       List<MeasurementUnitTypes> units, Set<Category> categories, byte[] image) {
        Recipe recipe = setRecipeVariables(recipeName, preparationTimeInMinutes, servings,
                preparationMethodSteps, ingredients, units, categories);
        recipe.setImageData(image);
        recipeRepository.save(recipe);
        return recipe;
    }



    private Recipe setRecipeVariables(String recipeName, int preparationTimeInMinutes, int servings,
                                      String[] preparationMethodSteps, List<RecipeIngredient> ingredients,
                                      List<MeasurementUnitTypes> units, Set<Category> categories) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setPreparationTimeInMinutes(preparationTimeInMinutes);
        recipe.setServings(servings);
        recipe.setPreparationMethodSteps(List.of(preparationMethodSteps));
        List<RecipeIngredient> ingredientSet = setRecipeIngredientsVariables(ingredients, units, recipe);
        recipe.setIngredients(ingredientSet);

        recipe.setCategories(categories);
        return recipe;
    }

    private List<RecipeIngredient> setRecipeIngredientsVariables(List<RecipeIngredient> ingredients,
                                                                List<MeasurementUnitTypes> units,
                                                                Recipe recipe) {
        for (int i = 0; i < ingredients.size(); i++) {
            ingredients.get(i).setRecipe(recipe);
            ingredients.get(i).setMeasurementUnit(units.get(i));
        }
        return ingredients;
    }

    private List<RecipeIngredient> makeListIngredients(Ingredient... ingredients) {
        List<RecipeIngredient> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setIngredient(ingredient);
            ingredientList.add(recipeIngredient);
        }
        return ingredientList;
    }

    private List<MeasurementUnitTypes> makeListUnits(MeasurementUnitTypes... units) {
        return Arrays.asList(units);
    }


    private Set<Category> makeSetCategories(Category... categories) {
        return new HashSet<>(Arrays.asList(categories));
    }

    private byte[] makeImageByteArray(String filename) throws IOException {
        File file = new File("ctrlalteat/src/main/projectDocuments/many2oneimages/" + filename);
        return Files.readAllBytes(file.toPath());
    }

    private void createImage(String filename) {
        try {
            this.image = makeImageByteArray(filename);
        } catch (IOException e) {
            throw new RuntimeException("couldn't read image", e);
        }
    }
}
