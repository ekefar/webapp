package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author: Alexander Serebriyan
 * Date: 03.05.12
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findByOfferUserId(Integer id);
}
