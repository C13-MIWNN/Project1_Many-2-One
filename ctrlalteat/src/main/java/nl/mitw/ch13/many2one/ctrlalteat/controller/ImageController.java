package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.model.Category;
import nl.mitw.ch13.many2one.ctrlalteat.model.Recipe;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.CategoryRepository;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.RecipeRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Arjan Cnossen
 * Purpose: Handle the uploading of images to recipes to the database
 **/

@Controller
public class ImageController {

        private final RecipeRepository recipeRepository;
        private final CategoryRepository categoryRepository;

        public ImageController(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
            this.recipeRepository = recipeRepository;
            this.categoryRepository = categoryRepository;
        }

        @GetMapping("/recipe/image/{recipeId}")
        public ResponseEntity<byte[]> getRecipeImageById(@PathVariable Long recipeId) {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
            if (recipeOptional.isPresent()) {
                Recipe recipe = recipeOptional.get();

                byte[] imageData = recipe.getImageData();
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @GetMapping("/category/image/{categoryId}")
    public ResponseEntity<byte[]> getCategoryImageById(@PathVariable Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            byte[] imageData = category.getImageData();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}