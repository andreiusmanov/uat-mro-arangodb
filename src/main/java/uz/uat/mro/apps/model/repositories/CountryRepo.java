package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Country;


public interface CountryRepo extends ArangoRepository<Country, String> {
   
}
