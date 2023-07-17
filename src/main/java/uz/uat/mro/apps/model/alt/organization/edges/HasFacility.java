package uz.uat.mro.apps.model.alt.organization.edges;

import com.arangodb.entity.From;
import com.arangodb.entity.Id;
import com.arangodb.entity.To;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;

import lombok.Data;
import uz.uat.mro.apps.model.alt.organization.Facility;
import uz.uat.mro.apps.model.alt.organization.Organization;

@Data
@Edge("has_facilities")
public class HasFacility {
     @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private Organization organization;
    @To
    private Facility facility;
}
