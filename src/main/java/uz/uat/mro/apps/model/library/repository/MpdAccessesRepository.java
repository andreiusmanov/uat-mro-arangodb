package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdAccess;

public interface MpdAccessesRepository extends ArangoRepository<MpdAccess, String> {

    @Query(value = "for i in mpd_accesses filter i.subzone.zone ==@zone return i")
    public List<MpdAccess> findByZone(String zone);

    @Query(value = "for i in mpd_accesses filter i.subzone == @subzone return i")
    public List<MpdAccess> findBySubzone(String subzone);

    @Query(value = "for i in mpd_accesses filter i.model == @model return i")
    public List<MpdAccess> findByModel(String model);
}
