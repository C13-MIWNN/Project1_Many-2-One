package nl.mitw.ch13.many2one.ctrlalteat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
@Entity
public class Ingredient {
    @Id @GeneratedValue
    private Long IngredientId;
    private String name;
    private double percentageProtein;
    private double percentageCarbohydrate;
    private double percentageFat;
    private String description;
    private String allergens;
    private String dietaryClassifications;


    public Ingredient(String name, double percentageProtein, double percentageCarbohydrate, double percentageFat, String description,
                      String allergens, String dietaryClassifications) {
        this.name = name;
        this.percentageProtein = percentageProtein;
        this.percentageCarbohydrate = percentageCarbohydrate;
        this.percentageFat = percentageFat;
        this.description = description;
        this.allergens = allergens;
        this.dietaryClassifications = dietaryClassifications;
    }

    public Ingredient() {
    }

    public Long getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(Long ingredientId) {
        IngredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentageProtein() {
        return percentageProtein;
    }

    public void setPercentageProtein(double gramProtein) {
        this.percentageProtein = gramProtein;
    }

    public double getPercentageCarbohydrate() {
        return percentageCarbohydrate;
    }

    public void setPercentageCarbohydrate(double gramCarbohydrate) {
        this.percentageCarbohydrate = gramCarbohydrate;
    }

    public double getPercentageFat() {
        return percentageFat;
    }

    public void setPercentageFat(double gramFat) {
        this.percentageFat = gramFat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public void setId(Long id) {
        this.IngredientId = id;
    }

    public Long getId() {
        return IngredientId;
    }

    public String getDietaryClassifications() {
        return dietaryClassifications;
    }

    public void setDietaryClassifications(String dietaryClassifications) {
        this.dietaryClassifications = dietaryClassifications;
    }

}
