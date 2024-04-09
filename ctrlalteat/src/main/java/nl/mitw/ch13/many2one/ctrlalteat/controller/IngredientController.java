package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
@Controller
public class IngredientController {
    private IngredientRepository ingredientRepository;


    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/ingredient")
    private String showIngredientsOverview(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        return "IngredientOverview";
    }

    @GetMapping("ingredient/new")
    private String showNewIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "IngredientForm";
    }


    @PostMapping("ingredient/new")
    private String saveIngredient(@ModelAttribute("ingredient")Ingredient ingredientToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            ingredientRepository.save(ingredientToBeSaved);
        }
        return "redirect:/ingredient";
    }
}
