package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.order.Offer;
import com.teamdev.webapp1.model.order.OfferStates;
import com.teamdev.webapp1.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 29.04.12
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public interface OfferRepository extends CrudRepository<Offer, Integer>{
    List<Offer> findByUserIdAndState(Integer userId, OfferStates state);


    @Query("select o from Offer o where o.user.id != ?")
    List<Offer> findNotBelongToUser(Integer userId);
    
    List<Offer> findByState(OfferStates state);

}
