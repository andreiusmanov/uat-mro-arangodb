package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdItem;

public interface MpdItemsRepository extends ArangoRepository<MpdItem, String> {
}
