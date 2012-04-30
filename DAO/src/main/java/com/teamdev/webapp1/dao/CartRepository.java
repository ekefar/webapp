package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.user.Cart;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Alexander Serebriyan
 * Date: 30.04.12
 */
public interface CartRepository extends CrudRepository<Cart, Integer> {
}
