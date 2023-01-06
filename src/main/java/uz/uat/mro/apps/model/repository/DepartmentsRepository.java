package uz.uat.mro.apps.model.repository;

import java.util.Collection;
import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Department;
import uz.uat.mro.apps.model.entity.Firm;

public interface DepartmentsRepository extends ArangoRepository<Department, String> {
    List<Department> findByFirm(Firm firm);
}
