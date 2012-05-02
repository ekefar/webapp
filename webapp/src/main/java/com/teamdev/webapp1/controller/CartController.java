package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamdev.webapp1.dao.CartDetailsRepository;
import com.teamdev.webapp1.dao.CartRepository;
import com.teamdev.webapp1.dao.OfferRepository;
import com.teamdev.webapp1.model.user.CartItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author: Alexander Serebriyan
 * Date: 30.04.12
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    private final OfferRepository offerRepository;
    private final CartDetailsRepository cartDetailsRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartController(OfferRepository offerRepository,
                          CartRepository cartRepository,
                          CartDetailsRepository cartDetailsRepository) {
        this.offerRepository = offerRepository;
        this.cartRepository = cartRepository;
        this.cartDetailsRepository = cartDetailsRepository;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String showAddDialog(@PathVariable(value = "id") int offerId,
                                Map<String, Object> model) {

        model.put("offer", offerRepository.findOne(offerId));
        return "cartForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addToCart(CartItems cartItems) {
        cartDetailsRepository.save(cartItems);
        return "cartForm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String saveRecordChanges(CartItems cartItems) {
        CartItems savedItem = cartDetailsRepository.save(cartItems);
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(savedItem);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCartRecord(@PathVariable(value = "id") int recordId,
                                 Map<String, Object> model) {

        model.put("record", cartDetailsRepository.findOne(recordId));
        return "/cart/cartRecordView";
    }

    @RequestMapping(value = "/view/{cartId}", method = RequestMethod.GET)
    public String cartView(@PathVariable(value = "cartId") int cartId,
                           Map<String, Object> model) {

        model.put("details", cartRepository.findOne(cartId).getItems());
        return "/cart/cartView";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String cartView(CartItems cartItems) {

        cartDetailsRepository.delete(cartItems);
        return "/cart/cartView";
    }
}
