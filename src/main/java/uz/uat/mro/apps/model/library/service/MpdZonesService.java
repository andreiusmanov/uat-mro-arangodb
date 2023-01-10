package uz.uat.mro.apps.model.library.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.aircraft.repository.MajorModelsRepository;
import uz.uat.mro.apps.model.library.entity.MpdAccesses;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.repository.MpdAccessesRepository;
import uz.uat.mro.apps.model.library.repository.MpdSubzonesRepository;
import uz.uat.mro.apps.model.library.repository.MpdZonesRepository;

@AllArgsConstructor
@Service
public class MpdZonesService {
    private MpdZonesRepository repo;
    private MpdSubzonesRepository subzonesRepo;
    private MpdAccessesRepository accessesRepo;
    private MajorModelsRepository modelsRepo;



    public List<MpdZone> findZoneByModel(MajorModel entity) {
        return repo.findByModel(entity.getArangoId());
    }
    
    public MpdZone save(MpdZone entity) {
        return repo.save(entity);
    }

    public void delete(MpdZone entity) {
        repo.delete(entity);
    }

    public MpdSubzone save(MpdSubzone entity) {
        return subzonesRepo.save(entity);
    }

    public void delete(MpdSubzone entity) {
        subzonesRepo.delete(entity);
    }

    public MpdAccesses save(MpdAccesses entity) {
        return accessesRepo.save(entity);
    }

    public void delete(MpdAccesses entity) {
        accessesRepo.delete(entity);
    }

    public Boolean isSyntheticAccess(MpdAccesses entity) {
        return entity.getSynthetic();
    }

public List<MajorModel> models(){
    return StreamSupport.stream(modelsRepo.findAll().spliterator(), false).toList();
}    
}
