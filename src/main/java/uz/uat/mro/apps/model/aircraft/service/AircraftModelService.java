package uz.uat.mro.apps.model.aircraft.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.aircraft.entity.AircraftModel;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.aircraft.repository.AircraftModelsRepository;
import uz.uat.mro.apps.model.aircraft.repository.MajorModelsRepository;

@AllArgsConstructor
@Service
public class AircraftModelService {
    private AircraftModelsRepository repo;
    private MajorModelsRepository modelRepo;

    public List<AircraftModel> findAircraftModelByMajorModel(MajorModel entity) {
        return repo.findByMajorModel(entity);
    }

    public AircraftModel saveAircraftModel(AircraftModel entity) {
        return repo.save(entity);
    }

    public void deleteAircraftModel(AircraftModel entity) {
        repo.delete(entity);
    }

    public List<AircraftModel> findAllAircraftModels() {
        Iterable<AircraftModel> iterable = repo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public List<MajorModel> findMajorModels() {
        Iterable<MajorModel> iterable = modelRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

}
