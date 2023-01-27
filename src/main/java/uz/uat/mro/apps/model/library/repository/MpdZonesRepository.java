package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdZone;

@Repository
public interface MpdZonesRepository extends ArangoRepository<MpdZone, String> {

    
    
    @Query(value = "for i in mpd_zones filter i.model == @model return i")
    public List<MpdZone> findByModel(@Param("model") MajorModel model);

    
    

}
