package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.model.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auteur Arjan Cnossen
 * Handle search requests on the website
 **/

@Controller
public class SearchController {

    private final Search searchService;

    public SearchController(Search searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String searchRecipes(Model model, @RequestParam("keyword") String keyword) {
        List<Recipe> recipeList = searchService.searchRecipes(keyword);
        model.addAttribute("recipeList", recipeList);

        if (recipeList.isEmpty()) {
            return "redirect:/404";
        }
        return "searchResults";
    }
}