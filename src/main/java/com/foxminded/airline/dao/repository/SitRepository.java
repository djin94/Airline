package com.foxminded.airline.dao.repository;

import com.foxminded.airline.domain.entity.Plane;
import com.foxminded.airline.domain.entity.Sit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SitRepository extends CrudRepository<Sit, Integer> {
    List<Sit> findByPlaneAndLevelTicket(Plane plane, String levelTicket);

    Sit findByPlaneAndPlace(Plane plane, String place);
}
