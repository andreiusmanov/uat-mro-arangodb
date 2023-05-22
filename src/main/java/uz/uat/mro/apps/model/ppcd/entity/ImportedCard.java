package uz.uat.mro.apps.model.ppcd.entity;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.marketing.entity.Project;

/**
 * Class for registration of imported data on maintenance cards
 * Simple String values
 */
@Data
@Document(value = "imported_cards")
public class ImportedCard {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String action;
    private String sequence;
    private String number;
    @Ref(lazy = false)
    private Revision revision;
    private String taskGroup;
    private String taskcard;
    private String taskCode;
    private String mhrs;
    private String description;
    private String remarks;
    private String status;
    @Ref(lazy = false)
    private Project project;

}
