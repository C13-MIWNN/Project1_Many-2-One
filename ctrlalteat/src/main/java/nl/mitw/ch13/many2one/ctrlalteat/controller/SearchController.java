package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.model.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auteur Arjan Cnossen
 * Purpose of the class
 **/
@Controller
public class SearchController {

    private final RecipeService recipeService;

    @Autowired
    public SearchController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/search")
    public String searchRecipes(Model model, @RequestParam("keyword") String keyword) {
        List<Recipe> recipeList = recipeService.searchRecipes(keyword);
        model.addAttribute("recipeList", recipeList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("title", "Search Results");
        return "SearchResults";
    }
}