package uz.uat.mro.apps.model.activity.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.docs.MaintenanceCard;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;
import uz.uat.mro.apps.model.marketing.entity.Project;

/**
 * Mtc - Manifacturers Taskcard. embedded object into {@link MaintenanceCard}
 */

@Data
@Document("maintenance_taskcard")
public class MaintenanceTaskcard {
    @Ref
    private Project project;
    @Ref
    private MpdTaskcard taskcard;
    private String number;
    private String workArea;
    private String skill;
}
