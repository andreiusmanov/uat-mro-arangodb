package uz.uat.mro.apps.model.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Firm;

public interface FirmsRepository extends ArangoRepository<Firm, String> {

}
