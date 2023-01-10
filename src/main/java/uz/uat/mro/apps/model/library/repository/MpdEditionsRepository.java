package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdEdition;

public interface MpdEditionsRepository extends ArangoRepository<MpdEdition, String> {

}
