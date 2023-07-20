package uz.uat.mro.apps.model.services.mpd;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.repository.MpdEditionRepo;

@Service
@AllArgsConstructor
public class MpdService {
    private MpdEditionRepo mpdEditionRepo;

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

}
