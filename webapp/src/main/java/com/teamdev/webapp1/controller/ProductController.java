package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.CategoriesRepository;
import com.teamdev.webapp1.dao.ProductRepository;
import com.teamdev.webapp1.dao.UnitRepository;
import com.teamdev.webapp1.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    private final UnitRepository unitRepository;
    private final CategoriesRepository categoriesRepository;
    private final ProductRepository productRepository;


    @Autowired
    public ProductController(UnitRepository unitRepository, CategoriesRepository categoriesRepository, ProductRepository productRepository) {
        this.unitRepository = unitRepository;
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddPage(Map<String, Object> model) {
        model.put("categoryList", categoriesRepository.findAll());
        model.put("unitList", unitRepository.findAll());
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCategory(@RequestParam(value = "product") String productName,
                              @RequestParam(value = "category") String categoryName,
                              @RequestParam(value = "unit") String unitName) {

        Product product = new Product(
                productName,
                unitRepository.findByName(unitName),
                categoriesRepository.findByName(categoryName));

        productRepository.save(product);
        return "addProduct";
    }
}
