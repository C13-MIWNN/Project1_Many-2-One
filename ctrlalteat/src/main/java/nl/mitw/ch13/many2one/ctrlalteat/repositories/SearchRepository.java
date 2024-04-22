package nl.mitw.ch13.many2one.ctrlalteat.repositories;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auteur Arjan Cnossen
 * Purpose of the class
 **/
public interface SearchRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT DISTINCT recipe FROM Recipe recipe"
            + " LEFT JOIN recipe.ingredients ingredient"
            + " LEFT JOIN recipe.categories category"
            + " WHERE recipe.recipeName LIKE %?1%"
            + " OR ingredient.ingredient.name LIKE %?1%"
            + " OR category.categoryName LIKE %?1%")
    List<Recipe> search(String keyword);
}
