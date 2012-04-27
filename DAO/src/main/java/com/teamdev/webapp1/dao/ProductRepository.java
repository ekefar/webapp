package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.product.Category;
import com.teamdev.webapp1.model.product.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByCategory(Category category);
}
