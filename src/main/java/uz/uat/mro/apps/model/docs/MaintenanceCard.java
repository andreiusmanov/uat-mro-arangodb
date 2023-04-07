package uz.uat.mro.apps.model.docs;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.activity.entity.MaintenanceArea;
import uz.uat.mro.apps.model.activity.entity.MaintenanceTaskcard;
import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.entity.TaskGroup;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;

@Data
@Document("maintenance_cards")
public class MaintenanceCard {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String sequence; // sequence number in resource file
    private String number; // maintenance card number
    @Ref(lazy = false)
    private TaskGroup taskGroup;
    @Ref(lazy = false)
    private MaintenanceArea maintenanceArea;
    @Ref(lazy = false)
    private Project project;
    @Ref(lazy = false)
    private MpdTaskcard taskcard; // linked reference from mpd taskcards
    private String taskcardString; // TASKCARD NUMBER FROM DOC
    private String mpReference;
    private String description; // description
    private String remarks; // all other information
    private Boolean valid; // indication of cancelled/active card
    private MaintenanceTaskcard manifacturersTaskcard; // Mtc record

}
