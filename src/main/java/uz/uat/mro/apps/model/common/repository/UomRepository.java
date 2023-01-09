package uz.uat.mro.apps.model.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Uom;

public interface UomRepository extends ArangoRepository<Uom, String> {

}
