package uz.uat.mro.apps.model.aircraft.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.Aircraft;

public interface AircraftsRepository extends ArangoRepository<Aircraft, String> {

    
}
