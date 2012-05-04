package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamdev.webapp1.dao.OrderRepository;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import com.teamdev.webapp1.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String completeOrder(@PathVariable("id") Integer orderId){
        return changeOrderState(orderId, OrderStates.COMPLETE);
    }

    @RequestMapping(value = "/deny/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String denyOrder(@PathVariable("id") Integer orderId){
        return changeOrderState(orderId, OrderStates.DENIED);
    }

    /**
     * Change order`s state to the new one.
     * @param orderId order`s id.
     * @param newState new order`s state.
     * @return  Json representation of changed and persisted order.
     */
    private String changeOrderState(int orderId, OrderStates newState){
        Order order = orderRepository.findOne(orderId);
        order.setState(newState);
        Order persistedOrder = orderRepository.save(order);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(persistedOrder);
    }

}
