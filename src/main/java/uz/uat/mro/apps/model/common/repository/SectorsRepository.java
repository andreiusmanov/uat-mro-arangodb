package uz.uat.mro.apps.model.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Department;
import uz.uat.mro.apps.model.entity.Sector;

public interface SectorsRepository extends ArangoRepository<Sector, String> {

    List<Sector> findByDepartment(Department department);

    @Query(value = "for i in sectors filter i.department == @departmentId return i")
    List<Sector> findByDepartment(@Param("departmentId") String departmentId);

}
