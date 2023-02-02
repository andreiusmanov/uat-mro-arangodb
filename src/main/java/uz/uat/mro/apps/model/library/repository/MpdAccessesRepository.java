package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.library.entity.MpdZone;

public interface MpdAccessesRepository extends ArangoRepository<MpdAccess, String> {

    @Query(value = "for i in mpd_accesses filter i.zone ==@zone return i")
    public List<MpdAccess> FindByZone(MpdZone zone);

    @Query(value = "for i in mpd_accesses filter i.subzone ==@subzone return i")
    public List<MpdAccess> FindBySubzone(MpdSubzone subzone);

    @Query(value = "for i in mpd_accesses filter i.model ==@model return i")
    public List<MpdAccess> FindByModel(MajorModel model);
}
