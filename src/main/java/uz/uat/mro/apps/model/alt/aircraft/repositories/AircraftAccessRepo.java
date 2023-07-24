package uz.uat.mro.apps.model.alt.aircraft.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.AircraftAccess;
import uz.uat.mro.apps.model.alt.aircraft.AircraftSubzone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;

public interface AircraftAccessRepo extends ArangoRepository<AircraftAccess, String> {

    List<AircraftAccess> findBySubzone(AircraftSubzone subzone);

    @Query(value = "for i in aircraft_accesses for s in aircraft_subzones for z in aircraft_zones filter i.subzone == s._id && s.zone == @zone return i")
    List<AircraftAccess> findByZone(@Param("zone") String zone);

    @Query(value = "for i in aircraft_accesses filter i.model == @model sort i.code return i")
    List<AircraftAccess> findByModel(String model);
}
