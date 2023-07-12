package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Station;

public interface StationRepo extends ArangoRepository<Station, String>{
    
}
