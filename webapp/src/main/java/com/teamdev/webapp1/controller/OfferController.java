package com.teamdev.webapp1.controller;

import com.teamdev.webapp1.dao.CategoriesRepository;
import com.teamdev.webapp1.dao.OfferRepository;
import com.teamdev.webapp1.dao.ProductRepository;
import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.order.OfferStates;
import com.teamdev.webapp1.model.product.Category;
import com.teamdev.webapp1.model.product.Product;
import com.teamdev.webapp1.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
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
    private final UserRepository userRepository;


    @Autowired
    public OfferController(CategoriesRepository categoriesRepository,
                           ProductRepository productRepository,
                           OfferRepository offerRepository,
                           UserRepository userRepository) {
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String showOfferAddPage(@PathVariable("id") Integer userId,
                                   Map<String, Object> model) {
        model.put("user", userRepository.findOne(userId));
        model.put("offer", new Offer());
        model.put("categoryList", categoriesRepository.findAll());
        return "/offer/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showOfferEditPage(@PathVariable("id") Integer offerId,
                                    Map<String, Object> model) {
        model.put("offer", offerRepository.findOne(offerId));
        return "/offer/edit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Offer addOffer(@Valid Offer offer, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        offer.setState(OfferStates.ACTIVE);
        offerRepository.save(offer);
        return offerRepository.findOne(offer.getId());
    }

    @RequestMapping("/listProducts")
    @ResponseBody
    public List<Product> listProducts(@Valid Category category) {
        return productRepository.findByCategory(category);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String catalog(Map<String, Object> model) {
        return "/catalog";
    }

    @RequestMapping(value = "/all/paging", method = RequestMethod.POST)
    @ResponseBody
    public String catalogPagingResponse(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "rp", defaultValue = "4") Integer size,
                                        @RequestParam(value = "sortname", defaultValue = "login") String orderBy,
                                        @RequestParam(value = "sortorder", defaultValue = "ASC") String direction,
                                        @RequestParam(value = "qtype", required = false) String searchBy,
                                        @RequestParam(value = "query", required = false) String searchValue) throws IOException {

        final Sort.Order order = new Sort.Order(Sort.Direction.fromString(direction.toUpperCase()), orderBy);
        final PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(order));
        final Page<Offer> users = offerRepository.findAll(pageRequest);
        return Utils.pageToJson(users);
    }

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    public String allOffers(@PathVariable("id") Integer userId,
                            Map<String, Object> model) {
        model.put("userId", userId);
        model.put("cartId", userRepository.findOne(userId).getCart().getId());
        return "/offer/all";
    }

    @RequestMapping(value = "/all/paging/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String allOffersPagingResponse(@PathVariable("id") Integer userId,
                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "rp", defaultValue = "4") Integer size,
                                          @RequestParam(value = "sortname", defaultValue = "login") String orderBy,
                                          @RequestParam(value = "sortorder", defaultValue = "ASC") String direction,
                                          @RequestParam(value = "qtype", required = false) String searchBy,
                                          @RequestParam(value = "query", required = false) String searchValue) throws IOException {

        final Sort.Order order = new Sort.Order(Sort.Direction.fromString(direction.toUpperCase()), orderBy);
        final PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(order));
        final Page<Offer> users = offerRepository.findNotBelongToUser(userId, pageRequest);
        return Utils.pageToJson(users);
    }


    @RequestMapping(value = "/own/{id}", method = RequestMethod.GET)
    public String listUserOffers(@PathVariable("id") Integer userId,
                                 Map<String, Object> model) {
        model.put("userId", userId);
        model.put("cartId", userRepository.findOne(userId).getCart().getId());
        model.put("offers", offerRepository.findByUserIdAndState(userId, OfferStates.ACTIVE));
        return "/offer/own";
    }

    @RequestMapping(value = "/own/paging", method = RequestMethod.POST)
    @ResponseBody
    public List<Offer> showAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "rp", defaultValue = "4") Integer size,
                               @RequestParam(value = "sortname", defaultValue = "title") String orderBy,
                               @RequestParam(value = "sortorder", defaultValue = "ASC") Sort.Direction direction) throws IOException {
        final Sort.Order order = new Sort.Order(direction, orderBy);
        final PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(order));
        final Page<Offer> offers = offerRepository.findAll(pageRequest);

        return offers.getContent();
    }


    @RequestMapping(value = "/deactivate", method = RequestMethod.POST)
    @ResponseBody
    public Offer deactivateOffer(@RequestParam("id") Integer offerId) {
        Offer offer = offerRepository.findOne(offerId);
        offer.setState(OfferStates.PASSIVE);
        return offerRepository.save(offer);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewOffer(@PathVariable("id") int offerId,
                            Map<String, Object> model) {
        final Offer offer = offerRepository.findOne(offerId);
        model.put("offer", offer);
        return "/offer/details";
    }

}
