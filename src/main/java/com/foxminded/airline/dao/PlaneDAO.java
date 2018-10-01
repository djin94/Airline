package com.foxminded.airline.dao;

import com.foxminded.airline.domain.service.Plane;
import org.springframework.data.repository.CrudRepository;

public interface PlaneDAO extends CrudRepository<Plane, Integer> {
}
