package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.CategoriesRepository;
import com.teamdev.webapp1.dao.ProductRepository;
import com.teamdev.webapp1.dao.UnitRepository;
import com.teamdev.webapp1.model.product.Category;
import com.teamdev.webapp1.model.product.Product;
import com.teamdev.webapp1.model.product.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
    public ProductController(UnitRepository unitRepository,
                             CategoriesRepository categoriesRepository,
                             ProductRepository productRepository) {
        this.unitRepository = unitRepository;
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
    }


    @RequestMapping("view")
    public String viewProducts(Map<String, Object> model) {
        model.put("products", productRepository.findAll());
        return "/product/view";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddPage(Map<String, Object> model) {
        model.put("product", new Product());
        model.put("categoryList", categoriesRepository.findAll());
        model.put("unitList", unitRepository.findAll());
        return "addProduct";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(Product product) {
        productRepository.save(product);
        return "redirect:/product/add";
    }

    @RequestMapping("/listCategories")
    @ResponseBody
    public List<Category> listCategories() {
        return categoriesRepository.findAll();
    }

    @RequestMapping("/listUnits")
    @ResponseBody
    public List<Unit> listUnits() {
        return unitRepository.findAll();
    }
}
