package uz.uat.mro.apps.model.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Country;
import uz.uat.mro.apps.model.entity.Station;

public interface StationsRepository extends ArangoRepository<Station, String> {

    List<Station> findByCountry(Country country);
}
