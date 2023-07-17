package uz.uat.mro.apps.model.alt.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;

public interface HasOrganizationUnitRepository extends ArangoRepository<OrganizationUnit, String>{
    
}
