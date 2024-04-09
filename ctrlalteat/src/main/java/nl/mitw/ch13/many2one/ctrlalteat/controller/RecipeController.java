package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Linda Munsterman
 * Purpose: Handle all requestes regarding recipes
 **/

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping({"/", "/recipe"})
    private String showRecipeOverview(Model model) {

        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }
}
