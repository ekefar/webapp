package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.dao.CategoriesRepository;
import com.teamdev.webapp1.dao.ProductRepository;
import com.teamdev.webapp1.dao.UnitRepository;
import com.teamdev.webapp1.model.product.Product;
import com.teamdev.webapp1.model.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

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
    public String showAddPage() {
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addProduct(HttpServletRequest request) throws IOException {
        String jsonProduct = request.getReader().readLine();
        jsonProduct = URLDecoder.decode(jsonProduct, "UTF-8");

        Product product = new Gson().fromJson(jsonProduct, Product.class);
        productRepository.save(product);
    }

    @RequestMapping("/listCategories")
    @ResponseBody
    public String listCategories() {
        return new Gson().toJson(categoriesRepository.findAll());
    }

    @RequestMapping("/listUnits")
    @ResponseBody
    public String listUnits() {
        return new Gson().toJson(unitRepository.findAll());
    }
}
