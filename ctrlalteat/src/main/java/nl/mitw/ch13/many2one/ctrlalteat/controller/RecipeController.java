package nl.mitw.ch13.many2one.ctrlalteat.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import nl.mitw.ch13.many2one.ctrlalteat.dtos.RecipeFormIngredientDTO;
import nl.mitw.ch13.many2one.ctrlalteat.enums.MeasurementUnitTypes;
import nl.mitw.ch13.many2one.ctrlalteat.model.*;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.CategoryRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeIngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import nl.mitw.ch13.many2one.ctrlalteat.services.RecipeFormService;
import nl.mitw.ch13.many2one.ctrlalteat.services.RecipePdfService;
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
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeFormService recipeFormService;
    private final List<MeasurementUnitTypes> measurementUnitTypes;

    public RecipeController(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, IngredientRepository ingredientRepository, CategoryRepository categoryRepository, RecipeFormService recipeFormService) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
        this.recipeFormService = recipeFormService;
        this.measurementUnitTypes = Arrays.asList(MeasurementUnitTypes.values());
    }

    @GetMapping("/recipe")
    private String showRecipeOverview(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return setupIngredientOverview(model);
    }

    private String setupIngredientOverview(Model model) {


        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("allIngredients", RecipeFormIngredientDTO.convertToRecipeFromIngredient(ingredients));
        model.addAttribute("measurementUnitTypes", measurementUnitTypes);
        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveRecipe(@ModelAttribute("recipe") @Valid Recipe recipeToBeSaved,
                              BindingResult result,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              @RequestParam(value = "ingredients", required = false) Long[] ingredients,
                              @RequestParam(value = "ingredientAmountInput", required = false) int[] ingredientAmountInput,
                              @RequestParam(value = "ingredientUnitInput", required = false) String[] units,
                              Model model) throws IOException {

        if (result.hasErrors()) {
            return setupIngredientOverview(model);
        }

        if (!imageFile.isEmpty()) {
            recipeToBeSaved.setImageData(imageFile.getBytes());
        }

        Set<Category> categories = handleCategory(recipeToBeSaved);
        recipeToBeSaved.setCategories(categories);

        Recipe savedRecipe = recipeRepository.save(recipeToBeSaved);
        recipeFormService.saveRecipeIngredients(ingredients, ingredientAmountInput, units, savedRecipe);

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
