package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.OrganizationUnit;

public interface OrganizationUnitRepo extends ArangoRepository<OrganizationUnit, String>{
    
}
