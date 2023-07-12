package uz.uat.mro.apps.model.alt.activity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.alt.marketing.Project;

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
