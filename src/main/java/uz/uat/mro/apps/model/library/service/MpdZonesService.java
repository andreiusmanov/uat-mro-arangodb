package uz.uat.mro.apps.model.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.aircraft.repository.MajorModelsRepository;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
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

    public List<MpdZone> findZoneByModel(String model) {
        return repo.findByModel(model);
    }

    public Map<String, MpdZone> getAllZones(String model) {
        Map<String, MpdZone> map = new HashMap<>();
        List<MpdZone> findByModel = repo.findByModel(model);
        for (MpdZone mpdZone : findByModel) {
            map.put(mpdZone.getCode(), mpdZone);
        }
        return map;
    }

    public Map<String, MpdSubzone> getAllSubzones(String model) {
        Map<String, MpdSubzone> map = new HashMap<>();
        List<MpdSubzone> findByModel = subzonesRepo.findByModel(model);
        for (MpdSubzone mpdZone : findByModel) {
            map.put(mpdZone.getCode(), mpdZone);
        }
        return map;
    }

    public MpdZone save(MpdZone entity) {
        return repo.save(entity);
    }

    public List<MpdZone> saveAllZones(List<MpdZone> entities) {
        return StreamSupport.stream(repo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdSubzone> saveAllSubzones(List<MpdSubzone> entities) {
        return StreamSupport.stream(subzonesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdAccess> saveAllAccesses(List<MpdAccess> entities) {
        return StreamSupport.stream(accessesRepo.saveAll(entities).spliterator(), false).toList();
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

    public List<MpdSubzone> findAllSubzones() {
        return StreamSupport.stream(subzonesRepo.findAll().spliterator(), false).toList();
    }

    public MpdAccess save(MpdAccess entity) {
        return accessesRepo.save(entity);
    }

    public void delete(MpdAccess entity) {
        accessesRepo.delete(entity);
    }

    public Boolean isSyntheticAccess(MpdAccess entity) {
        return entity.getSynthetic();
    }

    public List<MpdAccess> findAccessByZone(MpdZone zone) {
        return accessesRepo.FindByZone(zone);
    }

    public List<MpdAccess> findAccessBySubzone(MpdSubzone subzone) {
        return accessesRepo.FindBySubzone(subzone);
    }

    public List<MpdAccess> findAllAccessByModel(MajorModel model) {
        return accessesRepo.FindByModel(model);
    }

    public List<MajorModel> models() {
        return StreamSupport.stream(modelsRepo.findAll().spliterator(), false).toList();
    }
}
