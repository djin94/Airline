package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.LevelTicket;
import com.foxminded.airline.domain.entity.Plane;
import com.foxminded.airline.domain.entity.Sit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SitRepository extends CrudRepository<Sit, Integer> {
    List<Sit> findByPlaneAndLevelTicket(Plane plane, LevelTicket levelTicket);

    Sit findByPlaneAndPlace(Plane plane, String place);
}
