package uz.uat.mro.apps.model.ppcd.entity;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;
import uz.uat.mro.apps.model.marketing.entity.Project;

/**
 * Class for registration of imported data on maintenance cards
 * Simple String values 
 */
@Data
@Document(value = "imported_cards")
public class ImportedCards {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String action;
    private String sequence;
    private String number;
    private String revision;
    private String taskGroup;
    private String taskcard;
    private String taskCode;
    private String mhrs;
    private String description;
    private String remarks;
    private String status;
    private Project project;

}
