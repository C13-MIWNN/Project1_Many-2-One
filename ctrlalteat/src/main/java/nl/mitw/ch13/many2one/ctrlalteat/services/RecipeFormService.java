package nl.mitw.ch13.many2one.ctrlalteat.services;

import nl.mitw.ch13.many2one.ctrlalteat.enums.MeasurementUnitTypes;
import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.model.RecipeIngredient;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeIngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.model.IModel;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class RecipeFormService {
    RecipeIngredientRepository recipeIngredientRepository;
    IngredientRepository ingredientRepository;

    public RecipeFormService(IngredientRepository ingredientRepository,
                             RecipeIngredientRepository recipeIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public void saveRecipeIngredients(Long[] ingredients, int[] ingredientAmountInput, String[] units,
                                      Recipe savedRecipe) {
        for (int ingredientIndexNumber = 0; ingredientIndexNumber < ingredients.length; ingredientIndexNumber++) {
            Long ingredientId = ingredients[ingredientIndexNumber];
            Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);
            if (optionalIngredient.isPresent()){
                Ingredient ingredient = optionalIngredient.get();
                RecipeIngredient recipeIngredient = new RecipeIngredient();
                setRecipeIngredientValues(ingredientAmountInput, units, savedRecipe, recipeIngredient, ingredient,
                        ingredientIndexNumber);
                recipeIngredientRepository.save(recipeIngredient);
            }
        }
    }

    private static void setRecipeIngredientValues(int[] ingredientAmountInput, String[] units, Recipe savedRecipe,
                                                  RecipeIngredient recipeIngredient, Ingredient ingredient,
                                                  int ingredientIndexNumber) {
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setRecipe(savedRecipe);
        recipeIngredient.setAmount(ingredientAmountInput[ingredientIndexNumber]);
        MeasurementUnitTypes unit = MeasurementUnitTypes.valueOf(units[ingredientIndexNumber]);
        recipeIngredient.setMeasurementUnit(unit);
    }
}
