package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.user.CartItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Alexander Serebriyan
 * Date: 02.05.12
 */
public interface CartItemsRepository extends CrudRepository<CartItem, Integer> {
}