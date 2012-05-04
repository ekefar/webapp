package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamdev.webapp1.dao.CategoriesRepository;
import com.teamdev.webapp1.dao.OfferRepository;
import com.teamdev.webapp1.dao.ProductRepository;
import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.product.Category;
import com.teamdev.webapp1.model.user.Cart;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    public String showOfferAddPage(Map<String, Object> model, HttpServletRequest request) {
        model.put("user", userManager.getUser(request));
        model.put("offer", new Offer());
        model.put("categoryList", categoriesRepository.findAll());
        return "addOffer";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showOfferEditPage(@PathVariable("id") Integer offerId,
                                    Map<String, Object> model) {
        model.put("offer", offerRepository.findOne(offerId));
        return "offerEdit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addOffer(Offer offer) {
        final Offer persistedOffer = offerRepository.save(offer);
        final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(persistedOffer);
    }


    @RequestMapping("/listProducts")
    @ResponseBody
    public String listProducts(Category category) {
        return new Gson().toJson(productRepository.findByCategory(category));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String listAllOffers(Map<String, Object> model) {
        model.put("offers", offerRepository.findAll());
        return "/offer/all";
    }

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    public String listOffers(@PathVariable("id") Integer userId,
                             Map<String, Object> model) {
        model.put("userId", userId);
        model.put("cart", createUserCart(userId));
        model.put("offers", offerRepository.findNotBelongToUser(userId));
        return "/offer/all";
    }

    @RequestMapping(value = "/own/{id}", method = RequestMethod.GET)
    public String listUserOffers(@PathVariable("id") Integer userId,
                                 Map<String, Object> model) {
        model.put("userId", userId);
        model.put("cart", createUserCart(userId));
        model.put("offers", offerRepository.findByUserId(userId));
        return "/offer/own";
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewOffer(@PathVariable("id") int offerId,
                            Map<String, Object> model) {
        final Offer offer = offerRepository.findOne(offerId);
        model.put("offer", offer);
        return "offerDetails";
    }

    private Cart createUserCart(Integer userId) {
        User user = userRepository.findOne(userId);
        Cart cart;
        if (user.getCart() == null) {
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
