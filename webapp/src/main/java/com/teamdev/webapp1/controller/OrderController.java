package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.OrderRepository;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Author: Alexander Serebriyan
 * Date: 03.05.12
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;


    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @RequestMapping("/view/{id}")
    public String viewOrders(@PathVariable(value = "id") Integer userId,
                             Map<String, Object> model) {
        User user = new User();
        user.setId(userId);
        List<Order> orders = orderRepository.findByOfferUser(user);
        model.put("orders", orders);
        return "ordersView";
    }
}
