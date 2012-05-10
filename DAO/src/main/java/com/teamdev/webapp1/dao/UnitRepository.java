package com.teamdev.webapp1.dao;

import com.teamdev.webapp1.model.product.Unit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author: Alexander Serebriyan
 * Date: 24.04.12
 */
public interface UnitRepository extends CrudRepository<Unit, Integer> {
    Unit findByName(String name);
    List<Unit> findAll();
}
