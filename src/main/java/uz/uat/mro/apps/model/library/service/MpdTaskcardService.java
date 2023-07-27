package uz.uat.mro.apps.model.library.service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.alt.library.repository.MpdTaskcardsRepo;

@AllArgsConstructor
public class MpdTaskcardService {
    private MpdTaskcardsRepo repo;

    public MpdTaskcard save(MpdTaskcard entity) {
        return repo.save(entity);
    }

    public void delete(MpdTaskcard entity) {
        repo.delete(entity);
    }

}
