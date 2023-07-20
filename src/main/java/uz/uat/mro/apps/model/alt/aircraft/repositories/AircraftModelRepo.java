package uz.uat.mro.apps.model.alt.aircraft.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.AircraftModel;

public interface AircraftModelRepo extends ArangoRepository<AircraftModel, String>{
    
}
