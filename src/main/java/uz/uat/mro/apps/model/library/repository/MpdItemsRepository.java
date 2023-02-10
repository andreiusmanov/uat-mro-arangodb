package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.entity.MpdItem;

public interface MpdItemsRepository extends ArangoRepository<MpdItem, String> {

    List<MpdItem> findByEdition(MpdEdition edition);
}
