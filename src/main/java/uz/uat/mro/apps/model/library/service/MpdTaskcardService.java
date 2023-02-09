package uz.uat.mro.apps.model.library.service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;
import uz.uat.mro.apps.model.library.repository.MpdTaskcardsRepository;

@AllArgsConstructor
public class MpdTaskcardService {
    private MpdTaskcardsRepository repo;

    public MpdTaskcard save(MpdTaskcard entity) {
        return repo.save(entity);
    }

    public void delete(MpdTaskcard entity) {
        repo.delete(entity);
    }

}
