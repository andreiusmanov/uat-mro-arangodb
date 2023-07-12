package uz.uat.mro.apps.model.alt.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.OrganizationUnitName;

public interface OrganizationUnitNameRepo extends ArangoRepository<OrganizationUnitName, String>{
    
}
