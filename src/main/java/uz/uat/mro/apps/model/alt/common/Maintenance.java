package uz.uat.mro.apps.model.alt.common;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("maintenances")
public class Maintenance {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String code;
    private String index;
    private String description;
}
