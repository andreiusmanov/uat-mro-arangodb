package uz.uat.mro.apps.model.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Department;
import uz.uat.mro.apps.model.entity.Firm;

public interface DepartmentsRepository extends ArangoRepository<Department, String> {

    List<Department> findByFirm(Firm firm);

    @Query(value = "for i in departments FILTER i.firm == @firm RETURN i")
    public List<Department> findByFirmQuery(@Param("firm") String firm);

}
