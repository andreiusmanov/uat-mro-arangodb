package uz.uat.mro.apps.model.alt.aircraft.repositories;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;

public interface AircraftZonesRepo extends ArangoRepository<AircraftZone, String> {

    @Query("FOR zone IN aircraft_zones FILTER zone.model == @model sort zone.code RETURN zone")
    List<AircraftZone> findByModel(String model);

}
