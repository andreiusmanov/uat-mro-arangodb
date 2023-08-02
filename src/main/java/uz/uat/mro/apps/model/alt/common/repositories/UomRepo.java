package uz.uat.mro.apps.model.alt.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Uom;

public interface UomRepo extends ArangoRepository<Uom, String> {

}