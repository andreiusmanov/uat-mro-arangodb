package uz.uat.mro.apps.model.alt.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.Organization;

public interface OrganizationRepo extends ArangoRepository<Organization, String> {

}
