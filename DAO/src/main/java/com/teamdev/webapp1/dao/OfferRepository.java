package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.order.Offer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: gar
 * Date: 29.04.12
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public interface OfferRepository extends CrudRepository<Offer, Integer>{
}
