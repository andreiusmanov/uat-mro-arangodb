package uz.uat.mro.apps.model.common.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.Uom;

public interface UomRepository extends ArangoRepository<Uom, String> {

}
