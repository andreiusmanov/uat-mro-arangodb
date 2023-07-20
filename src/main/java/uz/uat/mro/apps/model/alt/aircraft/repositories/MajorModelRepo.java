package uz.uat.mro.apps.model.alt.aircraft.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.aircraft.MajorModel;


public interface MajorModelRepo extends ArangoRepository<MajorModel, String> {

}
