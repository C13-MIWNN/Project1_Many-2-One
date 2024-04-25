package nl.mitw.ch13.many2one.ctrlalteat.dtos;

import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IngredientDTO implements Comparable<IngredientDTO> {

    private Long ingredientId;
    private String name;
    private String kCal;
    private double protein;
    private double fats;
    private double carbs;

    public IngredientDTO(Long ingredientId, String name, String kCal, double protein, double fats, double carbs) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.kCal = kCal;
        this.protein = protein;
        this.fats = fats;
        this.carbs = carbs;
    }


    public static List<IngredientDTO> convertToIngredientDTO(List<Ingredient> ingredients) {
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            IngredientDTO recipeingredientDTO = new IngredientDTO(ingredient.getIngredientId(), ingredient.getName(),
                    ingredient.getkCal(), ingredient.getProtein(), ingredient.getFats(), ingredient.getCarbs());
            ingredientDTOS.add(recipeingredientDTO);
        }
        Collections.sort(ingredientDTOS);
        return ingredientDTOS;
    }


    @Override
    public int compareTo(IngredientDTO otherIngredient) {
        return this.name.compareTo(otherIngredient.name);
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

    public String getkCal() {
        return kCal;
    }

    public void setkCal(String kCal) {
        this.kCal = kCal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
}
