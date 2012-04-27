package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.product.Category;
import com.teamdev.webapp1.model.product.Unit;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */
public interface CategoriesRepository extends CrudRepository<Category, Integer> {
    Category findByName(String name);
}