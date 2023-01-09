package uz.uat.mro.apps.model.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.entity.Department;
import uz.uat.mro.apps.model.entity.Person;
import uz.uat.mro.apps.model.entity.Sector;
import uz.uat.mro.apps.model.repository.SectorsRepository;

@AllArgsConstructor
@Service
public class SectorService {
    private SectorsRepository repo;

    public List<Sector> findByDepartment(String departmentId) {
        return repo.findByDepartment(departmentId);
    }

    public Sector save(Sector entity) {
        return repo.save(entity);
    }

    public void delete(Sector entity) {
        repo.delete(entity);
    }

    public List<Sector> findAll() {
        Iterable<Sector> iterable = repo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public void addStaff(Sector sector, Person person) {
        sector.getStaff().add(person);
    }

    public void removeStaff(Sector sector, Person person) {
        sector.getStaff().remove(person);
    }
}
