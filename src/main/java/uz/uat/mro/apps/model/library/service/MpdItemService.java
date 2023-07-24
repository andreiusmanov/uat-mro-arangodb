package uz.uat.mro.apps.model.library.service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.library.repository.MpdItemsRepository;

@AllArgsConstructor
public class MpdItemService {
    private MpdItemsRepository repo;

    public MpdItem save(MpdItem entity) {
        return repo.save(entity);
    }

    public void delete(MpdItem entity) {
        repo.delete(entity);
    }
}
