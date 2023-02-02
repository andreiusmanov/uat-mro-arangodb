package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdSubzone;

public interface MpdSubzonesRepository extends ArangoRepository<MpdSubzone, String>{
    
}
