package uz.uat.mro.apps.model.services.mpd;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.aircraft.repositories.MajorModelRepo;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.alt.library.MpdMh;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.alt.library.repository.MpdEditionRepo;
import uz.uat.mro.apps.model.alt.library.repository.MpdItemsRepo;
import uz.uat.mro.apps.model.alt.library.repository.MpdMhsRepo;
import uz.uat.mro.apps.model.alt.library.repository.MpdTaskcardsRepo;

@Service
@AllArgsConstructor
public class MpdService {
    private MpdEditionRepo mpdEditionRepo;
    private MajorModelRepo majorModelRepo;
    private MpdMhsRepo mhsRepo;
    private MpdItemsRepo itemsRepo;
    private MpdTaskcardsRepo taskcardsRepo;

    // mpd edition

    public MpdEdition saveMpdEdition(MpdEdition mpdEdition) {
        return mpdEditionRepo.save(mpdEdition);
    }

    public void deleteMpdEdition(MpdEdition edition) {
        mpdEditionRepo.delete(edition);
    }

    public List<MpdEdition> getAllMpdEditions() {
        Iterable<MpdEdition> editions = mpdEditionRepo.findAll();
        return StreamSupport.stream(editions.spliterator(), false).toList();
    }

    public List<MpdEdition> getEditionsByModel(String model) {
        return mpdEditionRepo.getEditionsByModel(model);
    }

    // aircraft major models

    public List<MajorModel> findAllModels() {
        Iterable<MajorModel> models = majorModelRepo.findAll();
        return StreamSupport.stream(models.spliterator(), false).toList();
    }

    public List<MpdMh> findMhsByEdition(MpdEdition edition) {
        Iterable<MpdMh> mhs = mhsRepo.findMhByEdition(edition.getArangoId());
        return StreamSupport.stream(mhs.spliterator(), false).toList();
    }
    // mpd items

    public MpdItem saveMpdItem(MpdItem mpdItem) {
        return itemsRepo.save(mpdItem);
    }

    public void deleteMpdItem(MpdItem item) {
        itemsRepo.delete(item);
    }

    public List<MpdItem> findItemsByEdition(String edition) {
        return itemsRepo.findByEdition(edition);
    }

    public List<MpdTaskcard> findCardsByEdition(String edition) {
        return taskcardsRepo.findCardsByEdition(edition);
    }

}
