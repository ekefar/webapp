package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.user.CartItems;
import org.springframework.data.repository.CrudRepository;

/**
 * Author: Alexander Serebriyan
 * Date: 02.05.12
 */
public interface CartDetailsRepository extends CrudRepository<CartItems, Integer> {
}
