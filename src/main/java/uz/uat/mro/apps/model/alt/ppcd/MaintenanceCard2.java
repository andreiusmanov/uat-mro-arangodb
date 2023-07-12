package uz.uat.mro.apps.model.alt.ppcd;

import java.util.List;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.activity.PackageRevision;
import uz.uat.mro.apps.model.alt.activity.TaskGroup;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.alt.marketing.Project;

@Data
@Document("maintenance_cards")

public class MaintenanceCard2 {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String sequence; // serial number in work package
    private String number;
    @Ref(lazy = false)
    private PackageRevision revision; // revision under which the object was created
    @Ref(lazy = false)
    private TaskGroup taskGroup; // routine, hard time, engineering order, etc
    @Ref(lazy = false)
    private Project project;
    @Ref(lazy = false)
    private MpdTaskcard taskcard; // linked reference from mpd taskcards
    private String description; // description
    private String remarks; // all other information
    private CardOpen cardOpen;
    private CardClose cardClose;
    private List<CardPostpone> cardPostpone;
    private CardStatus cardStatus;
}
