package uz.uat.mro.apps.model.alt.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.Facility;

public interface FacilityRepo extends ArangoRepository<Facility, String>{
    
}
