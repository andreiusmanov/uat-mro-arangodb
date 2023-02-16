package uz.uat.mro.apps.model.common.service;

import java.util.List;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.common.entity.Maintenance;
import uz.uat.mro.apps.model.common.repository.MaintenanceRepository;

@AllArgsConstructor
public class MaintenanceService {
    private MaintenanceRepository repo;

    public Maintenance save(Maintenance entity) {
        return repo.save(entity);
    }

    public void delete(Maintenance entity) {
        repo.delete(entity);
    }

    public List<Maintenance> finadAll() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }
}
