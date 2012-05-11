package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.OfferRepository;
import com.teamdev.webapp1.dao.OrderRepository;
import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.order.OfferStates;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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
    private final OfferRepository offerRepository;


    @Autowired
    public OrderController(OrderRepository orderRepository,
                           OfferRepository offerRepository) {
        this.orderRepository = orderRepository;
        this.offerRepository = offerRepository;
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
        model.put("userId", userId);
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
    public String confirmOrder(@PathVariable("id") Integer orderId) {
        Order order = orderRepository.findOne(orderId);
        Offer offer = order.getOffer();

        Integer offerAmount = offer.getAmount();
        offer.setAmount(offerAmount - order.getAmount());
        order.setState(OrderStates.COMPLETE);

        if (offer.getAmount() == 0) {
            offer.setState(OfferStates.PASSIVE);
        }

        offerRepository.save(offer);
        orderRepository.save(order);
        return String.valueOf(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value = "/deny/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Order denyOrder(@PathVariable("id") Integer orderId) {
        return changeOrderState(orderId, OrderStates.DENIED);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Order deleteOrder(@PathVariable("id") Integer orderId) {
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
    public Order cancelOrder(@PathVariable("id") Integer orderId) {
        return changeOrderState(orderId, OrderStates.CANCELED);
    }

    /**
     * Change order`s state to the new one.
     *
     * @param orderId  order`s id.
     * @param newState new order`s state.
     * @return Json representation of changed and persisted order.
     */
    private Order changeOrderState(int orderId, OrderStates newState) {
        Order order = orderRepository.findOne(orderId);
        order.setState(newState);
        return orderRepository.save(order);
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
