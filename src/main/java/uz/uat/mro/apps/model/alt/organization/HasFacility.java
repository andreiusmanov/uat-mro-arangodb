package uz.uat.mro.apps.model.alt.organization;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge("has_facility")
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
