package uz.uat.mro.apps.model.alt.aircraft.repositories;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.AircraftAccess;
import uz.uat.mro.apps.model.alt.aircraft.AircraftSubzone;
import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;


public interface AircraftAccessRepo extends ArangoRepository<AircraftAccess, String> {

    List<AircraftAccess> findBySubzone(AircraftSubzone subzone);
    
    List<AircraftAccess> findByZone(AircraftZone zone);

    List<AircraftAccess> findByModel(MajorModel model);
}
