package uz.uat.mro.apps.model.alt.aircraft.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;

public interface AircraftZonesRepo extends ArangoRepository<AircraftZone, String>{
    
}
