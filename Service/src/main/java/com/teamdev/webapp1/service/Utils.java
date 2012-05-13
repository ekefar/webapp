package com.teamdev.webapp1.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamdev.webapp1.model.user.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * Created by Alexander Serebriyan
 * Date: 13.05.12
 */
public class Utils {
    /**
     * Prepare response for the Jquery table plugin on client side.
     * <p/>
     * Structure :
     * {
     * page: page number,
     * total: total rows,
     * rows: array of data
     * }
     *
     * @param page Page object that contains info about users.
     * @return Json representation of page.
     * @throws java.io.IOException
     */
    public static String pageToJson(Page page) throws IOException {
        JsonParser parser = new JsonParser();
        ObjectMapper mapper = new ObjectMapper();
        String contentString = mapper.writeValueAsString(page.getContent());
        JsonElement users = parser.parse(contentString);

        JsonObject jsonPage = new JsonObject();
        jsonPage.addProperty("total", page.getTotalElements());
        jsonPage.addProperty("page", page.getNumber() + 1);
        jsonPage.add("rows", users);

        return jsonPage.toString();
    }
}
