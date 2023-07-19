package uz.uat.mro.apps.model.alt.organization.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;

public interface OrganizationUnitRepo extends ArangoRepository<OrganizationUnit, String>{
 
    @Query("for u in has_unit for i in organization_units filter u._from == @organization && i._id == u._to return i")
    public List<OrganizationUnit> getOrganizationUnitsByOrganization(@Param("organization") String organization);
}
