package com.teamdev.webapp1.controller;

import com.google.gson.GsonBuilder;
import com.teamdev.webapp1.dao.*;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    private final UserRepository userRepository;


    @Autowired
    public CartController(OfferRepository offerRepository,
                          CartRepository cartRepository,
                          CartItemsRepository cartItemsRepository,
                          OrderRepository orderRepository,
                          UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.cartRepository = cartRepository;
        this.cartItemsRepository = cartItemsRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String showAddDialog(@PathVariable(value = "id") int offerId,
                                Map<String, Object> model) {

        model.put("offer", offerRepository.findOne(offerId));
        return "/cart/form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addToCart(@Valid CartItem cartItem, BindingResult result) {
        if (result.hasErrors()) {
            return String.valueOf(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }

        // check if cart already contains record with this offer
        CartItem itemForMerge = cartItemsRepository.findByOfferId(cartItem.getOffer().getId());

        // if it isn`t then save new item
        if (itemForMerge == null) {
            cartItemsRepository.save(cartItem);
        } else {

            // otherwise, add selected amount and update record in db
            Integer storedAmount = itemForMerge.getAmount();
            itemForMerge.setAmount(storedAmount + cartItem.getAmount());
            cartItemsRepository.save(itemForMerge);
        }

        return "/cart/form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String saveRecordChanges(@Valid CartItem cartItem, BindingResult result) {
        if (result.hasErrors()) {
            return String.valueOf(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }

        CartItem savedItem = cartItemsRepository.save(cartItem);
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(savedItem);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCartRecord(@PathVariable(value = "id") int recordId,
                                 Map<String, Object> model) {

        model.put("record", cartItemsRepository.findOne(recordId));
        return "/cart/viewItem";
    }

    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public String cartView(@PathVariable(value = "userId") int userId,
                           Map<String, Object> model) {

        model.put("cart", userRepository.findOne(userId).getCart());
        return "/cart/view";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String cartView(CartItem cartItem) {

        cartItemsRepository.delete(cartItem);
        return "/cart/view";
    }

    @RequestMapping(value = "/purchaseAll", method = RequestMethod.POST)
    @ResponseBody
    public int purchaseAll(@RequestParam(value = "cartId") Integer cartId) {
        Cart cart = cartRepository.findOne(cartId);
        CartItem[] cartItems = cart.getItems().toArray(new CartItem[0]);
        List<Order> orders = createOrders(cartItems);
        sendNotifications(orders);
        orderRepository.save(orders);
        clearCart(cart);
        return HttpServletResponse.SC_OK;
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @ResponseBody
    public String purchaseItem(@RequestParam(value = "id") Integer itemId) {
        CartItem item = cartItemsRepository.findOne(itemId);
        List<Order> orders = createOrders(item);
        sendNotifications(orders);
        orderRepository.save(orders);
        cartItemsRepository.delete(item);
        return String.valueOf(HttpServletResponse.SC_OK);
    }


    private void sendNotifications(List<Order> orders) {

    }

    /**
     * Remove all cart items
     *
     * @param cart cart to clear
     */
    private void clearCart(Cart cart) {
        List<CartItem> items = cart.getItems();
        cartItemsRepository.delete(items);
    }


    /**
     * Create batch of orders using user`s cart
     *
     * @param items items in user`s cart
     * @return list of orders
     */
    private List<Order> createOrders(CartItem... items) {
        List<Order> orders = new ArrayList<Order>();
        for (CartItem cartItem : items) {

            //create order from cart item
            Order order = new Order(cartItem.getOffer(), cartItem.getAmount());
            order.setCustomer(cartItem.getCart().getUser());
            order.setCreationDate(Calendar.getInstance().getTime());
            order.setState(OrderStates.PROCESSING);
            orders.add(order);
        }
        return orders;
    }

}
