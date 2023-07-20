package uz.uat.mro.apps.model.alt.aircraft.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.Aircraft;

public interface AircraftsRepo extends ArangoRepository<Aircraft, String>{
    
}
