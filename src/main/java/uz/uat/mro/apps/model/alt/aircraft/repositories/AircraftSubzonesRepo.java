package uz.uat.mro.apps.model.alt.aircraft.repositories;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.AircraftSubzone;

public interface AircraftSubzonesRepo extends ArangoRepository<AircraftSubzone, String> {

    @Query("FOR subzone IN aircraft_subzones FILTER subzone.model == @model sort subzone.code RETURN subzone")
    List<AircraftSubzone> findByModel(String model);
}
