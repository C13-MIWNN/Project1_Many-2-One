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
    private String description;
    private String measurementUnit;

    public Ingredient(Long ingredientId, String name, String description, String measurementUnit) {
        IngredientId = ingredientId;
        this.name = name;
        this.description = description;
        this.measurementUnit = measurementUnit;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
