package nl.mitw.ch13.many2one.ctrlalteat.model;

import jakarta.persistence.*;
import nl.mitw.ch13.many2one.ctrlalteat.enums.MeasurementUnitTypes;

import java.util.Set;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
@Entity
public class Ingredient {
    @Id @GeneratedValue
    private Long IngredientId;
    @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    private String name;
    private String description;
    private MeasurementUnitTypes measurementUnit;

    public Ingredient(Long ingredientId, String name, String description, MeasurementUnitTypes measurementUnit) {
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

    public MeasurementUnitTypes getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnitTypes measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
