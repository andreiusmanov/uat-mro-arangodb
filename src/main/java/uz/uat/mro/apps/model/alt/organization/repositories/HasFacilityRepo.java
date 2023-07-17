package uz.uat.mro.apps.model.alt.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.edges.HasFacility;

public interface HasFacilityRepo extends ArangoRepository<HasFacility, String> {
    
}
