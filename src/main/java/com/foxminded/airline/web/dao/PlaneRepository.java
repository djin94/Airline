package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Plane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends CrudRepository<Plane, Integer> {
}
