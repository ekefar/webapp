package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.CartRepository;
import com.teamdev.webapp1.dao.OfferRepository;
import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.CartDetails;
import com.teamdev.webapp1.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Author: Alexander Serebriyan
 * Date: 30.04.12
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    private final OfferRepository offerRepository;
    private final UserManager userManager;
    private final CartRepository cartRepository;

    @Autowired
    public CartController(OfferRepository offerRepository,
                          CartRepository cartRepository,
                          UserManager userManager) {
        this.offerRepository = offerRepository;
        this.cartRepository = cartRepository;
        this.userManager = userManager;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String showAddDialog(@PathVariable(value = "id") int offerId,
                                Map<String, Object> model) {
        model.put("offer", offerRepository.findOne(offerId));
        return "cartForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addToCart(@RequestParam(value = "offerId") Integer offerId,
                            @RequestParam(value = "amount") Integer amount,
                            @RequestParam(value = "cartId") Integer cartId) {

        Cart cart = cartRepository.findOne(cartId);
        CartDetails details = new CartDetails(new Offer(offerId), amount);
        details.setCart(cart);
        cart.add(details);
        cartRepository.save(cart);
        return "cartForm";
    }

    @RequestMapping(value = "/view/{cartId}", method = RequestMethod.GET)
    public String cartView(@PathVariable(value = "cartId") int cartId,
                           Map<String, Object> model) {
        model.put("details", cartRepository.findOne(cartId).getDetails());
        return "cartView";
    }
}
