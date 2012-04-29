package com.teamdev.webapp1.controller;

import com.google.gson.Gson;
import com.teamdev.webapp1.dao.CategoriesRepository;
import com.teamdev.webapp1.dao.OfferRepository;
import com.teamdev.webapp1.dao.ProductRepository;
import com.teamdev.webapp1.dao.UnitRepository;
import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.product.Category;
import com.teamdev.webapp1.model.product.Product;
import com.teamdev.webapp1.service.UserManager;
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


    @Autowired
    public OfferController(CategoriesRepository categoriesRepository,
                           ProductRepository productRepository,
                           OfferRepository offerRepository,
                           UserManager userManager) {
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
        this.userManager = userManager;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddOfferPage(Map<String, Object> model, HttpServletRequest request) {
        model.put("user", userManager.getUser(request));
        model.put("offer", new Offer());
        model.put("categoryList", categoriesRepository.findAll());
        return "addOffer";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOffer(Offer offer) {
        offerRepository.save(offer);
        return "addOffer";
    }

    @RequestMapping("/listProducts")
    @ResponseBody
    public String listProducts(Category category) {
        return new Gson().toJson(productRepository.findByCategory(category));
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String listOffers(Map<String, Object> model) {
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
}
