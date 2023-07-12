package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Organization;

public interface OrganizationRepo extends ArangoRepository<Organization, String>{
    
}
