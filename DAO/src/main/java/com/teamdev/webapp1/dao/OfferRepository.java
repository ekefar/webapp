package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.order.OfferStates;
import com.teamdev.webapp1.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {

    List<Offer> findByUserIdAndState(Integer userId, OfferStates state);

    @Query("select o from Offer o where o.user.id = :id and o.state in :states")
    Page<Offer> findByUserIdAndStateIn(@Param("id") Integer userId, @Param("states") Collection<OfferStates> states, Pageable pageable);

    @Override
    Page<Offer> findAll(Pageable pageable);

    @Query("select o from Offer o where o.user.id != ?")
    List<Offer> findNotBelongToUser(Integer userId);


    @Query("select o from Offer o where o.user.id != :id and o.product.name like :value")
    Page<Offer> findNotBelongToUserByProduct(@Param("id") Integer userId,@Param("value") String value, Pageable pageable);

    @Query("select o from Offer o where o.user.id != :id and o.product.category.name like :value")
    Page<Offer> findNotBelongToUserByCategory(@Param("id") Integer userId,@Param("value") String value, Pageable pageable);


    List<Offer> findByState(OfferStates state);
    
    

}
