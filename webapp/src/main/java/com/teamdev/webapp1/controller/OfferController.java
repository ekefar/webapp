package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.dao.*;
import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.product.Category;
import com.teamdev.webapp1.model.product.Product;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserManager;
import com.teamdev.webapp1.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Alexander Serebriyan
 * Date: 29.04.12
 */
@Controller
@RequestMapping("/offer")
public class OfferController {

    private final CategoriesRepository categoriesRepository;
    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;
    private final UserManager userManager;
    private final UserRepository userRepository;


    @Autowired
    public OfferController(CategoriesRepository categoriesRepository,
                           ProductRepository productRepository,
                           OfferRepository offerRepository,
                           UserManager userManager,
                           UserRepository userRepository) {
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
        this.userManager = userManager;
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddOfferPage(Map<String, Object> model, HttpServletRequest request) {
        model.put("user", userManager.getUser(request));
        model.put("offer", new Offer());
        model.put("categoryList", categoriesRepository.findAll());
        return "addOffer";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addOffer(Offer offer) {
        final Offer persistedOffer = offerRepository.save(offer);
        return new Gson().toJson(persistedOffer);
    }

    @RequestMapping("/listProducts")
    @ResponseBody
    public String listProducts(Category category) {
        return new Gson().toJson(productRepository.findByCategory(category));
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String listOffers(Map<String, Object> model,
                             HttpServletRequest request) {
        model.put("cart", createUserCart(request));
        model.put("offers", offerRepository.findAll());
        return "offersView";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewOffer(@PathVariable("id") int offerId,
                            Map<String, Object> model) {
        final Offer offer = offerRepository.findOne(offerId);
        model.put("offer", offer);
        return "offerDetails";
    }

    private Cart createUserCart(HttpServletRequest request){
        User user = userManager.getUser(request);
        Cart cart;
        if(user.getCart() == null){
            cart = new Cart();
            user.setCart(cart);
            User persistedUser = userRepository.save(user);
            cart = persistedUser.getCart();
        } else {
            cart = user.getCart();
        }

        return cart;
    }
}
