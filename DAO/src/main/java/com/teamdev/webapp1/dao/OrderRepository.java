package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.order.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Alexander Serebriyan
 * Date: 03.05.12
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
