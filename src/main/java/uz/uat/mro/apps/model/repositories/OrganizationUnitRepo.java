package uz.uat.mro.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.model.entities.common.OrganizationUnit;

public interface OrganizationUnitRepo extends ArangoRepository<OrganizationUnit, String>{
    
}
