package uz.uat.mro.apps.model.alt.organization;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("organization_units")
public class OrganizationUnit {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
    private String description;
    private String type;
    @Ref(lazy = false)
    private OrganizationUnitName unitName;
}
