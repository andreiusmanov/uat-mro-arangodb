package uz.uat.mro.apps.model.alt.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.edges.HasUnit;

public interface HasOrganizationUnitRepo extends ArangoRepository<HasUnit, String> {

}
