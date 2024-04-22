package nl.mitw.ch13.many2one.ctrlalteat.dtos;

import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.model.IngredientWithoutRecipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeFormIngredientDTO {
    private Long ingredientId;
    private String name;

    public RecipeFormIngredientDTO(String ingredientName, Long ingredientId) {
        this.name = ingredientName;
        this.ingredientId = ingredientId;
    }


    public static List<RecipeFormIngredientDTO> convertToRecipeFromIngredient(List<Ingredient> ingredients) {
        List<RecipeFormIngredientDTO> RecipeFormIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            RecipeFormIngredientDTO recipeFormIngredient = new RecipeFormIngredientDTO(ingredient.getName(),
                    ingredient.getIngredientId());
            RecipeFormIngredients.add(recipeFormIngredient);
        }
        return RecipeFormIngredients;
    }

    public Long getIngredientId() {

        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
