package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.Country;

public interface CountryRepo extends ArangoRepository<Country, String> {
   
}
