package uz.uat.mro.apps.model.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.entity.WorkDay;
import uz.uat.mro.apps.model.repository.WorkDayRepository;

@AllArgsConstructor
@Service
public class WorkDayService {
    private WorkDayRepository repo;

    public WorkDay save(WorkDay entity) {
        return repo.save(entity);
    }

    public void delete(WorkDay entity) {
        repo.delete(entity);
    }

    public List<WorkDay> findAll() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }

}
