package uz.uat.mro.apps.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<Department> findByFirm(Firm firm) {
        return repo.findByFirm(firm);
    }

}
