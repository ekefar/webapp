package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import com.teamdev.webapp1.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.List;

/**
 * Author: Alexander Serebriyan
 * Date: 03.05.12
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

    @Query("select o from Order  o where o.offer.user.id = :userId and o.state in :states")
    Page<Order> findByOfferUserIdAndStateIn(@Param("userId") Integer userId, Pageable page, @Param("states") Collection<OrderStates> states);

    List<Order> findByOfferUserId(Integer id);
    List<Order> findByCustomerId(Integer id);
}
