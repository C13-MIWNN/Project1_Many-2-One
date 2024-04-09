package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
@Controller
public class IngredientController {
    private IngredientRepository ingredientRepository;


    @GetMapping("/")
    private String showIngredientsOverview(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        return "IngredientOverview";
    }
}
