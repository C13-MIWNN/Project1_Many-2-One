package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Category;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.CategoryRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class homePageController {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public homePageController(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/")
    private String showRecipeOverview(Model model) {
        model.addAttribute("categoriesSubSet", getCategories());
        model.addAttribute("recipesSubSet", getRecipes());
        return "homePage";
    }

    private List<Category> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.size() < 6){
            return categories;
        }
        return categories.subList(0, 6);
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = recipeRepository.findAll();
        if (recipes.size() < 6){
            return recipes;
        }
        return recipes.subList(0, 6);
    }
}
