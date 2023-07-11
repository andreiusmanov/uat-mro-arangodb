package uz.uat.mro.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.model.entities.common.Station;

public interface StationRepo extends ArangoRepository<Station, String>{
    
}
