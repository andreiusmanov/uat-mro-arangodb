package uz.uat.mro.apps.model.alt.common;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

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
}
