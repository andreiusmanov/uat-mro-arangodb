package uz.uat.mro.apps.model.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.common.entity.Person;
import uz.uat.mro.apps.model.common.repository.PersonsRepository;

@AllArgsConstructor
@Service
public class PersonService {
    private PersonsRepository repo;

    public Person save(Person entity) {
        return repo.save(entity);
    }

    public void delete(Person entity) {
        repo.delete(entity);
    }

    public List<Person> findByDepartment(String departmentId) {
        return repo.findByDepartment(departmentId);
    }

}
