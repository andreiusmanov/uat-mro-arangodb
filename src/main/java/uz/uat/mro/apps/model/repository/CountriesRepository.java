package uz.uat.mro.apps.model.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Country;

public interface CountriesRepository extends ArangoRepository<Country, String> {

}
