package com.teamdev.webapp1.controller.response;


import com.teamdev.webapp1.model.product.Category;

import java.util.List;

public class CategoryChartInfo {

    public Category category;
    public List<ProductCountInfo> productsInfo;

    public CategoryChartInfo(Category category, List<ProductCountInfo> productsInfo) {
        this.category = category;
        this.productsInfo = productsInfo;
    }
}
