package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Category;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.CategoryRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author Linda Munsterman
 * Purpose: Handle all requestes regarding recipes
 **/

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final CategoryRepository categoryRepository;

    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/recipe")
    private String showRecipeOverview(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveRecipe(@ModelAttribute("recipe") Recipe recipeToBeSaved,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              BindingResult result,
                              Model model) throws IOException {

        if (result.hasErrors()) {
            return "recipeForm";
        }
        if (!imageFile.isEmpty()) {
            recipeToBeSaved.setImageData(imageFile.getBytes());
        }

        Set<Category> categories = handleCategory(recipeToBeSaved);
        recipeToBeSaved.setCategories(categories);

        recipeRepository.save(recipeToBeSaved);

        return "redirect:/";
    }

    private Set<Category> handleCategory(Recipe recipeToBeSaved) {
        String[] tags = unpackTags(recipeToBeSaved);
        List<Category> categories = categoryRepository.findAll();
        ArrayList<String> categoriesNames = new ArrayList<>();
        for (Category category : categories) {
            categoriesNames.add(category.getCategoryName());
        }
        Set<Category> categorySet = new HashSet<>();
        for (String tag : tags) {
            categorySet.add(getCategory(categoriesNames, tag, categories));
        }
        return categorySet;
    }

    private String[] unpackTags(Recipe recipeToBeSaved) {
        String tag = recipeToBeSaved.getTag();
        String[] tagListWithPossibleDuplicates = tag.split(",");
        return Arrays.stream(tagListWithPossibleDuplicates).distinct().toArray(String[]::new);
    }

    private Category getCategory(ArrayList<String> categoriesNames, String tag, List<Category> categories) {
        if (!categoriesNames.contains(tag)) {
            return createNewCategory(tag);
        } else {
            return getExistingCategory(tag, categories);
        }
    }

    private Category getExistingCategory(String categoryName, List<Category> categories) {
        Category existingCategory = null;
        for (Category category : categories) {
            if (category.getCategoryName() != null) {
                if (category.getCategoryName().equals(categoryName)) {
                    existingCategory = category;
                }
            }
        }
        return existingCategory;
    }

    private Category createNewCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        CategoryController categoryController = new CategoryController(categoryRepository);
        categoryController.saveCategoryFromRecipe(category);
        return category;
    }

    @GetMapping("/recipe/detail/{recipeId}")
    public String showRecipeDetails(@PathVariable("recipeId") Long recipeId, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findByRecipeId(recipeId);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe";
        }

        Recipe recipe = recipeOptional.get();
        byte[] imageData = recipe.getImageData();

        model.addAttribute("recipeToBeShown", recipe);
        model.addAttribute("recipeImageData", imageData);

        return "recipeDetails";
    }

    @GetMapping("/recipe/detail/edit/{recipeId}")
    public String showEditRecipe(@PathVariable("recipeId") Long recipeId, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe";
        }

        model.addAttribute("recipe", recipeOptional.get());
        model.addAttribute("allIngredients", ingredientRepository.findAll());

        return "recipeEdit";
    }

    @PostMapping("/recipe/detail/edit/{recipeId}")
    public String updateRecipe(@PathVariable("recipeId") Long recipeId,
                               @ModelAttribute("recipe") Recipe updatedRecipe,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               BindingResult result) throws IOException {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe";
        }

        Recipe existingRecipe = recipeOptional.get();

        existingRecipe.setRecipeName(updatedRecipe.getRecipeName());
        existingRecipe.setPreparationTimeInMinutes(updatedRecipe.getPreparationTimeInMinutes());
        existingRecipe.setServings(updatedRecipe.getServings());
        existingRecipe.setTag(updatedRecipe.getTag());
        existingRecipe.setPreparationMethodSteps(updatedRecipe.getPreparationMethodSteps());
        existingRecipe.setIngredients(updatedRecipe.getIngredients());

        if (!imageFile.isEmpty()) {
            existingRecipe.setImageData(imageFile.getBytes());
        }

        recipeRepository.save(existingRecipe);

        return "redirect:/recipe/detail/" + recipeId;
    }
}
