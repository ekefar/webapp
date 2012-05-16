package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.*;
import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.CartItem;
import com.teamdev.webapp1.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
    public CartItem saveRecordChanges(@Valid CartItem cartItem, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        return cartItemsRepository.save(cartItem);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCartRecord(@PathVariable(value = "id") int recordId,
                                 Map<String, Object> model) {

        model.put("record", cartItemsRepository.findOne(recordId));
        return "/cart/viewItem";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String cartView(@PathVariable(value = "id") Integer userId,
                           Map<String, Object> model) {
        model.put("cartId", userRepository.findOne(userId).getCart().getId());
        model.put("userId", userId);
        return "/cart/view";
    }

    @RequestMapping(value = "/view/paging/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String cartJsonResponse(@PathVariable(value = "id") Integer userId,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "rp", defaultValue = "4") Integer size,
                                   @RequestParam(value = "sortname", defaultValue = "login") String orderBy,
                                   @RequestParam(value = "sortorder", defaultValue = "ASC") String direction,
                                   @RequestParam(value = "qtype", required = false) String searchBy,
                                   @RequestParam(value = "query", required = false) String searchValue) throws IOException {

        final Sort.Order order = new Sort.Order(Sort.Direction.fromString(direction.toUpperCase()), orderBy);
        final PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(order));
        final Page<CartItem> cartItems = cartItemsRepository.findByCartId(userId, pageRequest);
        return Utils.pageToJson(cartItems);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public CartItem cartView(CartItem cartItem) {

        cartItemsRepository.delete(cartItem);
        return cartItem;
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
    public int purchaseItem(@RequestParam(value = "id") Integer itemId) {
        CartItem item = cartItemsRepository.findOne(itemId);
        List<Order> orders = createOrders(item);
        sendNotifications(orders);
        orderRepository.save(orders);
        cartItemsRepository.delete(item);
        return HttpServletResponse.SC_OK;
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
