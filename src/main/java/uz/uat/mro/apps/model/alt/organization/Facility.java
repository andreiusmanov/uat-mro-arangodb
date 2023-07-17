package uz.uat.mro.apps.model.alt.organization;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("facilities")
public class Facility {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
    private String description;
    @Ref
    private Organization organization;
}
