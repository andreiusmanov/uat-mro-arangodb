package uz.uat.mro.apps.model.library.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.aircraft.repository.MajorModelsRepository;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdMh;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.repository.MpdAccessesRepository;
import uz.uat.mro.apps.model.library.repository.MpdMhsRepository;
import uz.uat.mro.apps.model.library.repository.MpdSubzonesRepository;
import uz.uat.mro.apps.model.library.repository.MpdZonesRepository;

@AllArgsConstructor
@Service
public class MpdZonesService {
    private MpdZonesRepository repo;
    private MpdSubzonesRepository subzonesRepo;
    private MpdAccessesRepository accessesRepo;
    private MajorModelsRepository modelsRepo;
    private MpdMhsRepository mhsRepo;

    public List<MpdZone> findZoneByModel(String model) {
        return repo.findByModel(model);
    }

    public Map<String, MpdZone> getAllZones(String model) {
        return repo.findByModel(model).stream().collect(Collectors.toMap(MpdZone::getCode, Function.identity()));
    }

    public Map<String, MpdSubzone> getAllSubzones(String model) {
        return subzonesRepo.findSubzonesByModel(model).stream()
                .collect(Collectors.toMap(MpdSubzone::getCode, Function.identity()));
    }

    public MpdZone save(MpdZone entity) {
        return repo.save(entity);
    }

    public List<MpdZone> saveAllZones(Collection<MpdZone> entities) {
        return StreamSupport.stream(repo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdSubzone> saveAllSubzones(Collection<MpdSubzone> entities) {
        return StreamSupport.stream(subzonesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdAccess> saveAllAccesses(Collection<MpdAccess> entities) {
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

    public List<MpdSubzone> findAllSubzones(String model) {
        return subzonesRepo.findSubzonesByModel(model);
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

    public List<MpdAccess> findAccessByZone(String zone) {
        return accessesRepo.findByZone(zone);
    }

    public List<MpdAccess> findAccessBySubzone(String subzone) {
        return accessesRepo.findBySubzone(subzone);
    }

    public List<MpdAccess> findAllAccessByModel(String model) {
        List<MpdAccess> findByModel = accessesRepo.findByModel(model);
        return findByModel;
    }

    public List<MajorModel> models() {
        return StreamSupport.stream(modelsRepo.findAll().spliterator(), false).toList();
    }

    public List<MpdMh> findMhByEdition(String edition) {
        return StreamSupport.stream(mhsRepo.findMhByEdition(edition).spliterator(), false).toList();
    }

}
