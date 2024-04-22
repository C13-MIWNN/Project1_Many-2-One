package nl.mitw.ch13.many2one.ctrlalteat.model;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
public class IngredientWithoutRecipe {
    private Long ingredientId;
    private String name;

    public IngredientWithoutRecipe(String ingredientName, Long ingredientId) {
        this.name = ingredientName;
        this.ingredientId = ingredientId;
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
