package uz.uat.mro.apps.model.common.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.Country;

public interface CountriesRepository extends ArangoRepository<Country, String> {

}
