package nl.mitw.ch13.many2one.ctrlalteat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import nl.mitw.ch13.many2one.ctrlalteat.enums.MeasurementUnitTypes;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/

@Entity
public class RecipeIngredient {
    @Id @GeneratedValue
    private Long recipeIngredientId;

    int amount;
    MeasurementUnitTypes measurementUnit;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private Ingredient ingredient;

    private boolean measurementUnitIsItem() {
        return measurementUnit == MeasurementUnitTypes.Item;
    }

    public void setRecipeIngredientId(Long recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public Long getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MeasurementUnitTypes getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnitTypes measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        if (amount > 1 && measurementUnit == MeasurementUnitTypes.Item) {
            return String.format("%d %s %s", this.amount, "", this.ingredient + "s");
        } else if (amount <= 1 || measurementUnit == MeasurementUnitTypes.gram ||
                measurementUnit == MeasurementUnitTypes.milliliter) {
            return String.format("%d %s %s", this.amount, measurementUnitIsItem() ? "" : this.measurementUnit, this.ingredient);
        }
        return String.format("%d %s %s", this.amount, this.measurementUnit + "s", this.ingredient);
    }
}
