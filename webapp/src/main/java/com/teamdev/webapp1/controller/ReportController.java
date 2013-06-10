package com.teamdev.webapp1.controller;

import com.google.common.collect.ImmutableSet;
import com.teamdev.webapp1.controller.response.CategoryChartInfo;
import com.teamdev.webapp1.controller.response.ProductCountInfo;
import com.teamdev.webapp1.dao.OfferRepository;
import com.teamdev.webapp1.dao.OrderRepository;
import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.order.OfferStates;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import com.teamdev.webapp1.model.product.Category;
import com.teamdev.webapp1.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Author: Alexander Serebriyan
 * Date: 20.12.12
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/view/{id}")
    public String showReportsPage(@PathVariable("id") Integer userId, Map model) {
        model.put("userId", userId);
        return "/report/view";
    }

    @RequestMapping("/total/{id}")
    public String showTotalReport(@PathVariable("id") Integer userId) {
        return "/report/total";
    }

    @RequestMapping("/categories/{id}")
    public String showCategoriesReport(@PathVariable("id") Integer userId, Map model) {
        model.put("userId", userId);
        return "/report/categories";
    }

    @RequestMapping("/categories/bar/{id}")
    public String showCategoriesBarReport(@PathVariable("id") Integer userId, Map model) {
        model.put("userId", userId);
        return "/report/categoriesBar";
    }

    @RequestMapping("/pie/{id}")
    @ResponseBody
    public Map<Category, List<ProductCountInfo>> getPieChartModel(@PathVariable("id") Integer userId) {

        Map<Category, List<ProductCountInfo>> chartModel = new HashMap<Category, List<ProductCountInfo>>();

        List<Order> completeOrders = orderRepository.findByOfferUserIdAndStateIn(userId, ImmutableSet.of(OrderStates.COMPLETE));

        for (Order order : completeOrders) {
            Product product = order.getOffer().getProduct();
            Category category = product.getCategory();
            if (chartModel.get(category) == null ) {
                chartModel.put(category, new ArrayList<ProductCountInfo>());
            }


            boolean productMatch = false;
            for (ProductCountInfo productCountInfo : chartModel.get(category)) {
                if(productCountInfo.product.equals(product)) {
                    productCountInfo.count += order.getAmount();
                    productMatch = true;
                    break;
                }
            }

            if (!productMatch) {
                chartModel.get(category).add(new ProductCountInfo(product, order.getAmount()));
            }

        }

        return chartModel;
    }

}
