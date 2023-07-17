package uz.uat.mro.apps.model.alt.organization.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.Facility;

public interface FacilityRepo extends ArangoRepository<Facility, String> {

    @Query(value = "FOR f IN has_facilities FILTER f._from == @organization RETURN f._in")
    public List<Facility> getFacilitiesByOrganization(@Param("organization") String organization);
}
