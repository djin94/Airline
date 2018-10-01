package com.foxminded.airline.dao;

import com.foxminded.airline.domain.service.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {
}
