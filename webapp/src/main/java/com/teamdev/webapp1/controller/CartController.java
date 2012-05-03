package com.teamdev.webapp1.controller;

import com.google.gson.GsonBuilder;
import com.teamdev.webapp1.dao.CartItemsRepository;
import com.teamdev.webapp1.dao.CartRepository;
import com.teamdev.webapp1.dao.OfferRepository;
import com.teamdev.webapp1.dao.OrderRepository;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Author: Alexander Serebriyan
 * Date: 30.04.12
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    private final OfferRepository offerRepository;
    private final CartItemsRepository cartItemsRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;


    @Autowired
    public CartController(OfferRepository offerRepository,
                          CartRepository cartRepository,
                          CartItemsRepository cartItemsRepository,
                          OrderRepository orderRepository) {
        this.offerRepository = offerRepository;
        this.cartRepository = cartRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String showAddDialog(@PathVariable(value = "id") int offerId,
                                Map<String, Object> model) {

        model.put("offer", offerRepository.findOne(offerId));
        return "cartForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addToCart(CartItem cartItem) {
        cartItemsRepository.save(cartItem);
        return "cartForm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String saveRecordChanges(CartItem cartItem) {
        CartItem savedItem = cartItemsRepository.save(cartItem);
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(savedItem);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCartRecord(@PathVariable(value = "id") int recordId,
                                 Map<String, Object> model) {

        model.put("record", cartItemsRepository.findOne(recordId));
        return "/cart/cartRecordView";
    }

    @RequestMapping(value = "/view/{cartId}", method = RequestMethod.GET)
    public String cartView(@PathVariable(value = "cartId") int cartId,
                           Map<String, Object> model) {

        model.put("cart", cartRepository.findOne(cartId));
        return "/cart/cartView";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String cartView(CartItem cartItem) {

        cartItemsRepository.delete(cartItem);
        return "/cart/cartView";
    }

    @RequestMapping(value = "/order")
    public String makeOrder(@RequestParam(value = "cartId") Integer cartId) {
        Cart cart = cartRepository.findOne(cartId);

        List<Order> orders = createOrders(cart);
        sendNotifications(orders);
        orderRepository.save(orders);
        clearCart(cart);
        return "offerView";
    }


    private void sendNotifications(List<Order> orders){

    }

    /**
     * Remove all cart items
     * @param cart cart to clear
     */
    private void clearCart(Cart cart){
        List<CartItem> items = cart.getItems();
        cartItemsRepository.delete(items);
    }


    /**
     * Create batch of orders using user`s cart
     * @param cart User`s cart
     * @return  list of orders
     */
    private List<Order> createOrders(Cart cart) {
        List<Order> orders = new ArrayList<Order>();
        for(CartItem cartItem : cart.getItems()){

            //create order from cart item
            Order order = new Order(cartItem.getOffer(), cartItem.getAmount());
            order.setCustomer(cartItem.getOffer().getUser());
            order.setCreationDate(Calendar.getInstance().getTime());
            order.setState(OrderStates.PROCESSING);
            orders.add(order);
        }
        return orders;
    }

}
