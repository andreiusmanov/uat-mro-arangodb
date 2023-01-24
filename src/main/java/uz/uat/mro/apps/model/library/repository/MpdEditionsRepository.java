package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdEdition;

public interface MpdEditionsRepository extends ArangoRepository<MpdEdition, String> {

    @Query(value = "for i in mpd_editions filter i.model == @model return i")
    public List<MpdEdition> findByMajorModel(MajorModel model);

    
}
