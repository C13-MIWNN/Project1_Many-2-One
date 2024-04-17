package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Category;
import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.model.RecipeService;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.CategoryRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Simon Hiemstra
 * Purpose: Lets HTML and model.Category communicate
 **/

@Controller
public class CategoryController {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/category")
    private String showCategoryForm(Model model) {
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("newCategory", new Category());
        return "categoryForm";
    }

    @PostMapping("category/new")
    private String saveCategory(@ModelAttribute("category") Category categoryToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            categoryRepository.save(categoryToBeSaved);
        }
        return "redirect:/category";
    }

    public void saveCategoryFromRecipe(Category categoryToBeSaved) {
        categoryRepository.save(categoryToBeSaved);
    }


    @GetMapping("/category/{categoryName}")
    public String showCategoryRecipes(@PathVariable("categoryName") String categoryName, Model model) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
        if (categoryOptional.isEmpty()) {
            return "redirect:/category";
        }
        Category category = categoryOptional.get();
        model.addAttribute("category", category);


        List<Recipe> recipes = recipeRepository.findAllWithCategory(category.getCategoryName());

        model.addAttribute("categoryRecipes", recipes);

        return "categoryRecipes";
    }



//    public String showRecipeDetails(@PathVariable("recipeId") Long recipeId, Model model) {
//        Optional<Recipe> recipeOptional = recipeRepository.findByRecipeId(recipeId);
//
//        if (recipeOptional.isEmpty()) {
//            return "redirect:/recipe";
//        }
//
//        Recipe recipe = recipeOptional.get();
//        byte[] imageData = recipe.getImageData();
//
//        model.addAttribute("recipeToBeShown", recipe);
//        model.addAttribute("recipeImageData", imageData);
//
//        return "recipeDetails";
//    }
}