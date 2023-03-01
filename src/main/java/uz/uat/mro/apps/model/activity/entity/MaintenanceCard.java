package uz.uat.mro.apps.model.activity.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;

@Data
@Document("maintenance_cards")
public class MaintenanceCard {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String number;
    @Ref
    private MaintenanceArea maintenanceArea;
    @Ref
    private Project project;
    @Ref
    private MpdTaskcard taskcard; // linked reference from mpd taskcards
    private String taskcardString; // TASKCARD NUMBER FROM DOC
    private String mpReference;
    private String description; // description
    private String remarks; // all other information

}
