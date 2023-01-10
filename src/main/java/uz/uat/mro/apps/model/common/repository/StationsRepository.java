package uz.uat.mro.apps.model.common.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.Country;
import uz.uat.mro.apps.model.common.entity.Station;

public interface StationsRepository extends ArangoRepository<Station, String> {

    List<Station> findByCountry(Country country);
}
