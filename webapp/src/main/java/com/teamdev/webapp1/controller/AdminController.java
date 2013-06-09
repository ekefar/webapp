package com.teamdev.webapp1.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamdev.webapp1.controller.response.TableResponse;
import com.teamdev.webapp1.dao.UserRepository;
import com.teamdev.webapp1.model.user.User;
import com.teamdev.webapp1.service.Utils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userManager) {
        this.userRepository = userManager;
    }


    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String editUsers() {
        return "/admin/editUsers";
    }


    @RequestMapping(value = "/admin/editUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> listUsers() {
        return userRepository.findAll();
    }


    @RequestMapping(value = "/admin/editUsers", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateUserInfo(@RequestParam(value = "id") Integer userId,
                                  @RequestParam(value = "enabled") Boolean enabled) {

        User userToChange = userRepository.findOne(userId);
        userToChange.setEnabled(enabled);
        userRepository.save(userToChange);
        return userToChange.isEnabled();
    }

    @RequestMapping(value = "/admin/users/edit", method = RequestMethod.POST)
    @ResponseBody
    public TableResponse showAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "rp", defaultValue = "4") Integer size,
                                 @RequestParam(value = "sortname", defaultValue = "login") String orderBy,
                                 @RequestParam(value = "sortorder", defaultValue = "ASC") String direction,
                                 @RequestParam(value = "qtype", required = false) String searchBy,
                                 @RequestParam(value = "query", required = false) String searchValue) throws IOException {

        final Sort.Order order = new Sort.Order(Sort.Direction.fromString(direction.toUpperCase()), orderBy);
        final PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(order));

        final Page<User> users = "".equals(searchValue) ? userRepository.findAll(pageRequest) : userRepository.searchByLogin("%" + searchValue + "%", pageRequest);

        return new TableResponse(users);
    }



    @RequestMapping("/admin/userProfile/{id}")
    public String viewUserProfile(@PathVariable("id") Integer userId,
                                  Map<String, Object> model) {
        model.put("user", userRepository.findOne(userId));
        return "/user/home";
    }
}
