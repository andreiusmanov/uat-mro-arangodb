package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdMh;

public interface MpdMhsRepository extends ArangoRepository<MpdMh, String>{
    
}
