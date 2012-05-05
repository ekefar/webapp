package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamdev.webapp1.dao.OrderRepository;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    public String viewOrders(@PathVariable("id") Integer userId,
                             Map<String, Object> model) {
        model.put("userId", userId);
        return "/order/view";
    }

    @RequestMapping("/processing/{id}")
    public String viewProcessingOrders(@PathVariable(value = "id") Integer userId,
                                       Map<String, Object> model) {

        List<Order> orders = orderRepository.findByOfferUserId(userId);
        model.put("orders", findOrdersWithState(orders, OrderStates.PROCESSING));
        return "/order/processing";
    }

    @RequestMapping("/confirmed/{id}")
    public String viewConfirmedOrders(@PathVariable(value = "id") Integer userId,
                                      Map<String, Object> model) {

        List<Order> orders = orderRepository.findByOfferUserId(userId);
        model.put("orders", findOrdersWithState(orders, OrderStates.COMPLETE, OrderStates.DENIED));
        return "/order/confirmed";
    }


    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String completeOrder(@PathVariable("id") Integer orderId) {
        return changeOrderState(orderId, OrderStates.COMPLETE);
    }

    @RequestMapping(value = "/deny/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String denyOrder(@PathVariable("id") Integer orderId) {
        return changeOrderState(orderId, OrderStates.DENIED);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteOrder(@PathVariable("id") Integer orderId) {
        return changeOrderState(orderId, OrderStates.DELETED);
    }

    @RequestMapping("/purchase/view/{id}")
    public String viewPurchases(@PathVariable("id") Integer userId,
                             Map<String, Object> model) {
        model.put("userId", userId);
        return "/order/purchase/view";
    }

    @RequestMapping("/purchase/active/{id}")
    public String viewActivePurchases(@PathVariable(value = "id") Integer cunstomerId,
                                       Map<String, Object> model) {

        List<Order> orders = orderRepository.findByCustomerId(cunstomerId);
        model.put("orders", findOrdersWithState(orders, OrderStates.PROCESSING));
        return "/order/purchase/active";
    }

    @RequestMapping("/purchase/past/{id}")
    public String viewPastPurchases(@PathVariable(value = "id") Integer cunstomerId,
                                      Map<String, Object> model) {

        List<Order> orders = orderRepository.findByCustomerId(cunstomerId);
        model.put("orders", findOrdersWithState(orders, OrderStates.COMPLETE, OrderStates.DENIED, OrderStates.CANCELED));
        return "/order/purchase/past";
    }

    @RequestMapping(value = "/purchase/cancel/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String cancelOrder(@PathVariable("id") Integer orderId) {
        return changeOrderState(orderId, OrderStates.CANCELED);
    }

    /**
     * Change order`s state to the new one.
     *
     * @param orderId  order`s id.
     * @param newState new order`s state.
     * @return Json representation of changed and persisted order.
     */
    private String changeOrderState(int orderId, OrderStates newState) {
        Order order = orderRepository.findOne(orderId);
        order.setState(newState);
        Order persistedOrder = orderRepository.save(order);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(persistedOrder);
    }


    /**
     * Find and return orders with specified states
     *
     * @param orders Orders list
     * @param states target state
     * @return List of orders with specified states
     */
    private List<Order> findOrdersWithState(List<Order> orders, OrderStates... states) {
        List<Order> result = new ArrayList<Order>();

        for (Order o : orders) {
            if (ArrayUtils.contains(states, o.getState())) {
                result.add(o);
            }
        }

        return result;
    }

}
