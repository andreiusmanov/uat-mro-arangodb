package uz.uat.mro.apps.model.alt.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Country;

public interface CountriesRepository extends ArangoRepository<Country, String> {
    public Country findByShortName(String shortName);
}
