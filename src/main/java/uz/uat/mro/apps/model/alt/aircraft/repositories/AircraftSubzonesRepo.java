package uz.uat.mro.apps.model.alt.aircraft.repositories;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.AircraftSubzone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;


public interface AircraftSubzonesRepo extends ArangoRepository<AircraftSubzone, String>{
    List<AircraftSubzone> findByModel(MajorModel model);
}
