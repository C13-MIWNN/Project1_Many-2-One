package nl.mitw.ch13.many2one.ctrlalteat.repositories;

import nl.mitw.ch13.many2one.ctrlalteat.model.Category;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
Optional<Recipe> findByRecipeId(Long recipeId);

    @Query(" SELECT recipe FROM Recipe recipe" +
            " LEFT JOIN recipe.categories category" +
            " WHERE category.categoryName = ?1")
    List<Recipe> findAllWithCategory(String keyword);

}
