package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
