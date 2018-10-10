package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer>{
}
