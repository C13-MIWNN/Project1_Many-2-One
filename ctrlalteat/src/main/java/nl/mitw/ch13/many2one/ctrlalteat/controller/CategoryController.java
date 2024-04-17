package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Category;
import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Simon Hiemstra
 * Purpose: Lets HTML and model.Category communicate
 **/

@Controller
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
}
