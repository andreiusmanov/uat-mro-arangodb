package uz.uat.mro.apps.model.library.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.aircraft.AircraftAccess;
import uz.uat.mro.apps.model.alt.aircraft.AircraftSubzone;
import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftAccessRepo;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftSubzonesRepo;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftZonesRepo;
import uz.uat.mro.apps.model.alt.aircraft.repositories.MajorModelRepo;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdMh;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.repository.MpdMhsRepository;

@AllArgsConstructor
@Service
public class ZonesService {
    private AircraftZonesRepo repo;
    private AircraftSubzonesRepo subzonesRepo;
    private AircraftAccessRepo accessesRepo;
    private MajorModelRepo modelsRepo;
    private MpdMhsRepository mhsRepo;

    public List<AircraftZone> findZoneByModel(MajorModel model) {
        return repo.findByModel(model);
    }

    public Map<String, AircraftZone> getAllZones(MajorModel model) {
        return repo.findByModel(model).stream().collect(Collectors.toMap(AircraftZone::getCode, Function.identity()));
    }

    public Map<String, AircraftSubzone> getAllSubzones(MajorModel model) {
        return subzonesRepo.findByModel(model).stream()
                .collect(Collectors.toMap(AircraftSubzone::getCode, Function.identity()));
    }

    public AircraftZone saveZone(AircraftZone entity) {
        return repo.save(entity);
    }

    public List<AircraftZone> saveAllZones(Collection<AircraftZone> entities) {
        return StreamSupport.stream(repo.saveAll(entities).spliterator(), false).toList();
    }

    public List<AircraftSubzone> saveAllSubzones(Collection<AircraftSubzone> entities) {
        return StreamSupport.stream(subzonesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<AircraftAccess> saveAllAccesses(Collection<AircraftAccess> entities) {
        return StreamSupport.stream(accessesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public void deleteZone(AircraftZone entity) {
        repo.delete(entity);
    }

    public AircraftSubzone saveSubzone(AircraftSubzone entity) {
        return subzonesRepo.save(entity);
    }

    public void deleteSubzone(AircraftSubzone entity) {
        subzonesRepo.delete(entity);
    }

    public List<AircraftSubzone> findAllSubzones(MajorModel model) {
        return subzonesRepo.findByModel(model);
    }

    public AircraftAccess saveAccess(AircraftAccess entity) {
        return accessesRepo.save(entity);
    }

    public void deleteAccess(AircraftAccess entity) {
        accessesRepo.delete(entity);
    }

    public Boolean isSyntheticAccess(AircraftAccess entity) {
        return entity.getSynthetic();
    }

    public List<AircraftAccess> findAccessByZone(AircraftZone zone) {
        return accessesRepo.findByZone(zone);
    }

    public List<AircraftAccess> findAccessesBySubzone(AircraftSubzone subzone) {
        return accessesRepo.findBySubzone(subzone);
    }

    public List<AircraftAccess> findAllAccessByModel(MajorModel model) {
        List<AircraftAccess> findByModel = accessesRepo.findByModel(model);
        return findByModel;
    }

    public List<MajorModel> getModels() {
        return StreamSupport.stream(modelsRepo.findAll().spliterator(), false).toList();
    }

    public List<MpdMh> findMhByEdition(String edition) {
        return StreamSupport.stream(mhsRepo.findMhByEdition(edition).spliterator(), false).toList();
    }

}
