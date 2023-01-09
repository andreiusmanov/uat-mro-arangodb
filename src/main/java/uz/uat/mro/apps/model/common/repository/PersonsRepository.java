package uz.uat.mro.apps.model.common.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.Person;

public interface PersonsRepository extends ArangoRepository<Person, String>{
    
    @Query(value = "for i in works FILTER i.from ==@departmentId return i.to")
public List<Person> findByDepartment(@Param("departmentId")String departmentId);
}
