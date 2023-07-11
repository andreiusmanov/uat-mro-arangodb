package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.model.entities.common.Organization;

public interface OrganizationRepo extends ArangoRepository<Organization, String>{
    
}
