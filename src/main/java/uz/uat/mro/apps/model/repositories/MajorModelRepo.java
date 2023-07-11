package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;

public interface MajorModelRepo extends ArangoRepository<MajorModel, String> {

}
