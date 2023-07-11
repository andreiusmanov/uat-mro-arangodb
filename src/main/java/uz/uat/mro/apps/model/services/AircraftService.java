package uz.uat.mro.apps.model.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.repositories.MajorModelRepo;

@Service
@AllArgsConstructor

public class AircraftService {
    private MajorModelRepo majorModelRepo;

    public MajorModel saveMajorModel(MajorModel majorModel) {
        return majorModelRepo.save(majorModel);
    }

    public MajorModel updateMajorModel(MajorModel majorModel) {
        return majorModelRepo.save(majorModel);
    }

    public void deleteMajorModel(MajorModel majorModel) {
        majorModelRepo.delete(majorModel);
    }
}