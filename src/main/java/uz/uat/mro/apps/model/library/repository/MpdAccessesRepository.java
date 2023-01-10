package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdAccesses;

public interface MpdAccessesRepository extends ArangoRepository<MpdAccesses, String> {

}
