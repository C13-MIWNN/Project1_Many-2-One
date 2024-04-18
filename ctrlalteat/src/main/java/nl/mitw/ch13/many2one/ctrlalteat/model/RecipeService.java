package nl.mitw.ch13.many2one.ctrlalteat.model;

import nl.mitw.ch13.many2one.ctrlalteat.repositories.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Auteur Arjan Cnossen
 * Purpose of the class
 **/

@Service
public class RecipeService {

    private SearchRepository searchRepository;

    @Autowired
    public RecipeService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }
    public List<Recipe> searchRecipes(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return searchRepository.search("%" + keyword + "%");
        }
        return Collections.emptyList();
    }
}