package uz.uat.mro.apps.model.aircraft.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.entity.Firm;

public interface MajorModelsRepository extends ArangoRepository<MajorModel, String> {

    List<MajorModel> findByProducer(Firm producer);

    @Query(value = "for i in firms return i")
    public List<Firm> findProducers();

    @Query(value = "for i in firms filter i.producer == @producer return i")
    public List<Firm> findByProducer(@Param("producer") String producer);
}
