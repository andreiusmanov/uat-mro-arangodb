package uz.uat.mro.apps.model.aircraft.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.entity.Firm;

public interface MajorModelsRepository extends ArangoRepository<MajorModel, String> {

    List<MajorModel> findByProducer(Firm producer);
}
