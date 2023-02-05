package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdSubzone;

public interface MpdSubzonesRepository extends ArangoRepository<MpdSubzone, String>{
    @Query(value = "for i in mpd_subzones filter i.model == @model return i")
    public List<MpdSubzone> findByModel(@Param("model") String model);

}
