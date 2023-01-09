package uz.uat.mro.apps.model.aircraft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.aircraft.entity.AircraftModel;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.aircraft.repository.AircraftModelsRepository;

@AllArgsConstructor
@Service
public class AircraftModelService {
    private AircraftModelsRepository repo;

    public List<AircraftModel> findByMajorModel(MajorModel entity) {
        return repo.findByMajorModel(entity);
    }

    public AircraftModel save(AircraftModel entity) {
        return repo.save(entity);
    }

    public void delete(AircraftModel entity) {
        repo.delete(entity);
    }

}
