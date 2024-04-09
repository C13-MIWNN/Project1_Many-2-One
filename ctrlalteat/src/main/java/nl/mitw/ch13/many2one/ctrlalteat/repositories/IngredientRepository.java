package nl.mitw.ch13.many2one.ctrlalteat.repositories;

import nl.mitw.ch13.many2one.ctrlalteat.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Simon Hiemstra
 * Purpose:
 **/
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
