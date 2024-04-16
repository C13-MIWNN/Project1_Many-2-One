package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Linda Munsterman
 * Purpose: Handle all requestes regarding recipes
 **/

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping({"/", "/recipe"})
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

        recipeRepository.save(recipeToBeSaved);

        return "redirect:/";
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


}
