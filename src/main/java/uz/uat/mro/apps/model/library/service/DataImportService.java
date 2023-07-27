package uz.uat.mro.apps.model.library.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.alt.library.MpdMh;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.alt.library.repository.MpdEditionRepo;
import uz.uat.mro.apps.model.alt.library.repository.MpdItemsRepo;
import uz.uat.mro.apps.model.library.repository.MpdMhsRepository;
import uz.uat.mro.apps.model.library.repository.MpdTaskcardsRepository;

@AllArgsConstructor
@Service
public class DataImportService {
    private AircraftZonesRepo zonesRepo;
    private AircraftSubzonesRepo subzonesRepo;
    private AircraftAccessRepo accessesRepo;
    private MpdItemsRepo itemsRepo;
    private MpdTaskcardsRepository taskcardsRepo;
    private MpdMhsRepository mhsRepo;
    private MajorModelRepo majorModelRepo;
    private MpdEditionRepo editionRepo;

    public Map<String, AircraftZone> getAllZones(MajorModel model) {
        Map<String, AircraftZone> map = new HashMap<>();
        List<AircraftZone> zones = zonesRepo.findByModel(model.getArangoId());
        for (AircraftZone zone : zones) {
            map.put(zone.getCode(), zone);
        }
        return map;
    }

    public Map<String, AircraftSubzone> getAllSubzones(MajorModel model) {
        List<AircraftSubzone> subzones = subzonesRepo.findByModel(model.getArangoId());
        return subzones.stream().collect(Collectors.toMap(AircraftSubzone::getCode, subzone -> subzone));
    }

    public void setMpdItems2Taskcards(MpdEdition edition) {
        taskcardsRepo.setMpdItems2Taskcards(edition.getArangoId());
    }

    public void setMpdItems2Mhs(MpdEdition edition) {
        mhsRepo.setMpdItems2Mhs(edition.getArangoId());
    }

    public List<AircraftZone> saveAllZones(Set<AircraftZone> entities) {
        return StreamSupport.stream(zonesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<AircraftSubzone> saveAllSubzones(Set<AircraftSubzone> entities) {
        return StreamSupport.stream(subzonesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<AircraftAccess> saveAllAccesses(Set<AircraftAccess> entities) {
        return StreamSupport.stream(accessesRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdItem> saveAllMpdItems(List<MpdItem> entities) {
        return StreamSupport.stream(itemsRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdTaskcard> saveAllTaskcards(List<MpdTaskcard> entities) {
        return StreamSupport.stream(taskcardsRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdMh> saveAllMhs(List<MpdMh> entities) {
        return StreamSupport.stream(mhsRepo.saveAll(entities).spliterator(), false).toList();
    }

    public MpdItem findByNumberAndEdition(String number, MpdEdition edition) {
        Object items = itemsRepo.findByEdition2(edition.getArangoId());
        return null;
        // return StreamSupport.stream(items.spliterator(), false).filter(e ->
        // e.getNumber().equals(number)).findFirst()
        // .get();
    }

    public List<MajorModel> getMajorModels() {
        Iterable<MajorModel> models = majorModelRepo.findAll();
        return StreamSupport.stream(models.spliterator(), false).toList();
    }

    public List<MpdEdition> getMpdEditions() {
        Iterable<MpdEdition> editions = editionRepo.findAll();
        return StreamSupport.stream(editions.spliterator(), false).toList();
    }

}
