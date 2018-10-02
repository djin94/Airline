package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Plane;
import org.springframework.data.repository.CrudRepository;

public interface PlaneDAO extends CrudRepository<Plane, Integer> {
}
