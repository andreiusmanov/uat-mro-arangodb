package uz.uat.mro.apps.model.ppcd.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.activity.entity.MaintenanceArea;
import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.activity.entity.TaskGroup;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.marketing.entity.Project;

@Data
@Document("maintenance_cards")
public class MaintenanceCard {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String sequence; // serial number in work package
    private String number;
    @Ref(lazy = false)
    private Revision revision; // revision under which the object was created
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
    private String taskCode;
    private String mhrs;
    private String description; // description
    private String remarks; // all other information
    private String status;

}
