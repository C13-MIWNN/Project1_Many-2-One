package nl.mitw.ch13.many2one.ctrlalteat.model;


import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Simon Hiemstra
 * Purpose: Reprisents possible catagories that recipes can have which the recipes can get sorted by.
 **/
@Entity
public class Category {

    @Id @GeneratedValue
    private Long categoryId;
    private String categoryName;
    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    public Category() {
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
