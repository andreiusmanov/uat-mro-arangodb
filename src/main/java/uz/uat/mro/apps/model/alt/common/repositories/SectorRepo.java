package uz.uat.mro.apps.model.alt.common.repositories;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Sector;

public interface SectorRepo extends ArangoRepository<Sector, String> {

    @Query(value = "FOR s IN sectors FILTER s.departmentId == @departmentId RETURN s")
    List<Sector> findByDepartmentId(String departmentId);

}
