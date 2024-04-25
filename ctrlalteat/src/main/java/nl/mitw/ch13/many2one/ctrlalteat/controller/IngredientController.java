package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.dtos.IngredientDTO;
import nl.mitw.ch13.many2one.ctrlalteat.dtos.RecipeFormIngredientDTO;
import nl.mitw.ch13.many2one.ctrlalteat.enums.MeasurementUnitTypes;
import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @author Simon Hiemstra
 * Purpose: Lets HTML and model.Ingredient communicate
 **/
@Controller
public class IngredientController {
    private IngredientRepository ingredientRepository;
    private List<MeasurementUnitTypes> measurementUnitTypes;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.measurementUnitTypes = Arrays.asList(MeasurementUnitTypes.values());
    }

    @GetMapping("/ingredient")
    private String showIngredientsOverview(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("allIngredients", IngredientDTO.convertToIngredientDTO(ingredients));
        model.addAttribute("newIngredient", new Ingredient());
        model.addAttribute("measurementUnitTypes", measurementUnitTypes);
        return "ingredientOverview";
    }

    @PostMapping("ingredient/new")
    private String saveIngredient(@ModelAttribute("ingredient") Ingredient ingredientToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            ingredientRepository.save(ingredientToBeSaved);
        }
        return "redirect:/ingredient";
    }
}
