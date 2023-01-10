package uz.uat.mro.apps.model.aircraft.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.AircraftModel;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;

public interface AircraftModelsRepository extends ArangoRepository<AircraftModel, String> {

    List<AircraftModel> findByMajorModel(MajorModel majorModel);

}
