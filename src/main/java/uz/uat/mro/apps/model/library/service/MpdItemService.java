package uz.uat.mro.apps.model.library.service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.alt.library.repository.MpdItemsRepo;

@AllArgsConstructor
public class MpdItemService {
    private MpdItemsRepo repo;

    public MpdItem save(MpdItem entity) {
        return repo.save(entity);
    }

    public void delete(MpdItem entity) {
        repo.delete(entity);
    }
}
