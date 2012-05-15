package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.user.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Author: Alexander Serebriyan
 * Date: 02.05.12
 */
public interface CartItemsRepository extends PagingAndSortingRepository<CartItem, Integer> {

    @Query("select c from CartItem  c where c.cart.id = :id")
    Page<CartItem> findByCartId(@Param("id") Integer id, Pageable pageable);

    CartItem findByOfferId(Integer offerId);
}
