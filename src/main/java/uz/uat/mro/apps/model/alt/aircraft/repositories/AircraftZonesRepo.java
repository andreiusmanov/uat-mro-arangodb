package uz.uat.mro.apps.model.alt.aircraft.repositories;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;

public interface AircraftZonesRepo extends ArangoRepository<AircraftZone, String> {

    List<AircraftZone> findByModel(MajorModel model);

}
