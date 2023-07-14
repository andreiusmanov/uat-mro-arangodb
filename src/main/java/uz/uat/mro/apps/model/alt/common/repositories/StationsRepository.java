package uz.uat.mro.apps.model.alt.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Country;
import uz.uat.mro.apps.model.alt.common.Station;

public interface StationsRepository extends ArangoRepository<Station, String> {

    Country findByCountry(Country country);
}
