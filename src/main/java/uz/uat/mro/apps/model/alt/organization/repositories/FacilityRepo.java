package uz.uat.mro.apps.model.alt.organization.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.organization.Facility;

public interface FacilityRepo extends ArangoRepository<Facility, String> {

    @Query(value = "FOR h IN has_facilities for f in facilities FILTER h._from == @organization and f._id == h._to RETURN f")
    public List<Facility> getFacilitiesByOrganization(@Param("organization") String organization);
}
