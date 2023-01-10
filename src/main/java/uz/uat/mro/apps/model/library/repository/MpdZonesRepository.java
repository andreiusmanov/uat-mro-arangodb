package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdZone;

public interface MpdZonesRepository extends ArangoRepository<MpdZone, String> {

    @Query(value = "for i in mpd_zones filter model ==@model return i")
    public List<MpdZone> findByModel(@Param("model") String model);

}
