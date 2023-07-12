package uz.uat.mro.apps.model.alt.organization.edges;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;

@Data
@Edge("has_unit_unit")
public class HasUnitUnit {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private OrganizationUnit organizationUnitFrom;
    @To
    private OrganizationUnit organizationUnitTo;
}
