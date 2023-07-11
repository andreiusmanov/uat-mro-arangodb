package uz.uat.mro.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.model.entities.common.Uom;

public interface UomRepo extends ArangoRepository<Uom, String>{
    
}
