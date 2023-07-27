package uz.uat.mro.apps.model.services.mpd;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.aircraft.repositories.MajorModelRepo;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdMh;
import uz.uat.mro.apps.model.alt.library.repository.MpdEditionRepo;
import uz.uat.mro.apps.model.alt.library.repository.MpdMhsRepo;

@Service
@AllArgsConstructor
public class MpdService2 {
    private MpdEditionRepo mpdEditionRepo;
    private MajorModelRepo majorModelRepo;
    private MpdMhsRepo mhsRepo;

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
}
