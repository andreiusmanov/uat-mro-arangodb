package uz.uat.mro.apps.model.library.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.repository.MpdAccessesRepository;
import uz.uat.mro.apps.model.library.repository.MpdItemsRepository;
import uz.uat.mro.apps.model.library.repository.MpdSubzonesRepository;
import uz.uat.mro.apps.model.library.repository.MpdTaskcardsRepository;
import uz.uat.mro.apps.model.library.repository.MpdZonesRepository;

public class DataImportService {
    private MpdZonesRepository zonesRepo;
    private MpdSubzonesRepository subzonesRepo;
    private MpdAccessesRepository accessesRepo;
    private MpdItemsRepository itemsRepo;
    private MpdTaskcardsRepository taskcardsRepo;


    public Map<String, MpdZone> getAllZones(String model) {
        Map<String, MpdZone> map = new HashMap<>();
        List<MpdZone> findByModel = zonesRepo.findByModel(model);
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

    public List<MpdZone> saveAllZones(Collection<MpdZone> entities) {
        return StreamSupport.stream(zonesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdSubzone> saveAllSubzones(Collection<MpdSubzone> entities) {
        return StreamSupport.stream(subzonesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdAccess> saveAllAccesses(Collection<MpdAccess> entities) {
        return StreamSupport.stream(accessesRepo.saveAll(entities).spliterator(), false).toList();
    }

}
