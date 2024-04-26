package nl.mitw.ch13.many2one.ctrlalteat.model;

import nl.mitw.ch13.many2one.ctrlalteat.repositories.SearchRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Auteur Arjan Cnossen
 * Handles search requests on the website
 **/

@Service
public class Search {

    private SearchRepository searchRepository;

    public Search(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }
    public List<Recipe> searchRecipes(String keyword) {
        if (keyword != null) {
            return searchRepository.search(keyword);
        }
        return Collections.emptyList();
    }
}