package uz.uat.mro.apps.model.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.arangodb.springframework.annotation.Query;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.entity.Department;
import uz.uat.mro.apps.model.entity.Firm;
import uz.uat.mro.apps.model.repository.DepartmentsRepository;

@AllArgsConstructor
@Service
public class DepartmentService {
    private DepartmentsRepository repo;

    public Department save(Department entity) {
        return repo.save(entity);
    }

    public void delete(Department entity) {
        repo.delete(entity);
    }

    public List<Department> findByFirm(@Param("firm") String firm) {
        return repo.findByFirmQuery(firm);
    }

    public List<Department> findAll() {
        Iterable<Department> iterable = repo.findAll();
        List<Department> list = StreamSupport.stream(iterable.spliterator(), false).toList();
        System.out.println("размер списка " + list.size());
        return list;
    }

}
