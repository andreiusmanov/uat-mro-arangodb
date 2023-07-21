package uz.uat.mro.apps.model.library.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.aircraft.repositories.MajorModelRepo;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.repository.MpdEditionRepo;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdItem;
import uz.uat.mro.apps.model.library.entity.MpdMh;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.repository.MpdAccessesRepository;
import uz.uat.mro.apps.model.library.repository.MpdItemsRepository;
import uz.uat.mro.apps.model.library.repository.MpdMhsRepository;
import uz.uat.mro.apps.model.library.repository.MpdSubzonesRepository;
import uz.uat.mro.apps.model.library.repository.MpdTaskcardsRepository;
import uz.uat.mro.apps.model.library.repository.MpdZonesRepository;

@AllArgsConstructor
@Service
public class DataImportService {
    private MpdZonesRepository zonesRepo;
    private MpdSubzonesRepository subzonesRepo;
    private MpdAccessesRepository accessesRepo;
    private MpdItemsRepository itemsRepo;
    private MpdTaskcardsRepository taskcardsRepo;
    private MpdMhsRepository mhsRepo;
    private MajorModelRepo majorModelRepo;
    private MpdEditionRepo editionRepo;

    public Map<String, MpdZone> getAllZones(String model) {
        Map<String, MpdZone> map = new HashMap<>();
        List<MpdZone> findByModel = zonesRepo.findByModel(model);
        for (MpdZone mpdZone : findByModel) {
            map.put(mpdZone.getCode(), mpdZone);
        }
        return map;
    }

    public Map<String, MpdSubzone> getAllSubzones(String model) {
        List<MpdSubzone> findByModel = subzonesRepo.findSubzonesByModel(model);
        return findByModel.stream().collect(Collectors.toMap(MpdSubzone::getCode, subzone -> subzone));
    }

    public Map<String, MpdItem> getAllMpdItems(MpdEdition edition) {
        List<MpdItem> itemsList = StreamSupport
                .stream(itemsRepo.getMpdItems(edition.getArangoId()).spliterator(), false).toList();
        return itemsList.stream().collect(Collectors.toMap(MpdItem::getNumber, mpdItem -> mpdItem));
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

    public List<MpdItem> saveAllMpdItems(Collection<MpdItem> entities) {
        return StreamSupport.stream(itemsRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdTaskcard> saveAllTaskcards(Collection<MpdTaskcard> entities) {
        return StreamSupport.stream(taskcardsRepo.saveAll(entities).spliterator(), false).toList();
    }

    public List<MpdMh> saveAllMhs(Collection<MpdMh> entities) {
        return StreamSupport.stream(mhsRepo.saveAll(entities).spliterator(), false).toList();
    }

    public MpdItem findByNumberAndEdition(String number, MpdEdition edition) {
        return itemsRepo.findByEdition(edition.getArangoId()).stream().filter(e -> e.getNumber().equals(number))
                .findFirst().get();
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
