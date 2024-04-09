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
    private double gramProtein;
    private double gramCarbohydrate;
    private double gramFat;
    private String description;
    private String allergens;
    private String dietairyClassifications;


    public Ingredient(String name, double gramProtein, double gramCarbohydrate, double gramFat, String description,
                      String allergens, String dietairyClassifications) {
        this.name = name;
        this.gramProtein = gramProtein;
        this.gramCarbohydrate = gramCarbohydrate;
        this.gramFat = gramFat;
        this.description = description;
        this.allergens = allergens;
        this.dietairyClassifications = dietairyClassifications;
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

    public double getGramProtein() {
        return gramProtein;
    }

    public void setGramProtein(double gramProtein) {
        this.gramProtein = gramProtein;
    }

    public double getGramCarbohydrate() {
        return gramCarbohydrate;
    }

    public void setGramCarbohydrate(double gramCarbohydrate) {
        this.gramCarbohydrate = gramCarbohydrate;
    }

    public double getGramFat() {
        return gramFat;
    }

    public void setGramFat(double gramFat) {
        this.gramFat = gramFat;
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

    public String getDietairyClassifications() {
        return dietairyClassifications;
    }

    public void setDietairyClassifications(String dietairyClassifications) {
        this.dietairyClassifications = dietairyClassifications;
    }

    public void setId(Long id) {
        this.IngredientId = id;
    }

    public Long getId() {
        return IngredientId;
    }
}
