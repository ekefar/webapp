package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.order.Order;
import com.teamdev.webapp1.model.order.OrderStates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Author: Alexander Serebriyan
 * Date: 03.05.12
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {

    @Query("select o from Order  o where o.offer.user.id = :userId and o.state in :states")
    Page<Order> findByOfferUserIdAndStateIn(@Param("userId") Integer userId, Pageable page, @Param("states") Collection<OrderStates> states);

    @Query("select o from Order  o where o.offer.user.id = :userId and o.state in :states")
    List<Order> findByOfferUserIdAndStateIn(@Param("userId") Integer userId, @Param("states") Collection<OrderStates> states);

    @Query("select o from Order  o where o.customer.id = :customerId and o.state in :states")
    Page<Order> findByCustomerIdAndStateIn(@Param("customerId") Integer customerId, Pageable page, @Param("states") Collection<OrderStates> states);


    List<Order> findByOfferUserId(Integer id);

    List<Order> findByCustomerId(Integer id);
}
