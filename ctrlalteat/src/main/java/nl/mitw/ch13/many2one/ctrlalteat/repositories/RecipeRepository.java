package nl.mitw.ch13.many2one.ctrlalteat.repositories;

import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
