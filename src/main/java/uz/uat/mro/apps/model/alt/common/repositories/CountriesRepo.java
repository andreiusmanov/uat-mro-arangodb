package uz.uat.mro.apps.model.alt.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Country;

public interface CountriesRepo extends ArangoRepository<Country, String> {
    public Country findByShortName(String shortName);
}
