package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Plane;
import org.springframework.data.repository.CrudRepository;

public interface PlaneRepository extends CrudRepository<Plane, Integer> {
}
