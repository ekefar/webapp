package com.teamdev.webapp1.controller.response;

import com.teamdev.webapp1.model.product.Product;

public class ProductCountInfo {
    public Product product;
    public int count;


    public ProductCountInfo(Product product, int count) {
        this.product = product;
        this.count = count;
    }
}
