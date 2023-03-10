package uz.uat.mro.apps.model.activity.entity;

import lombok.Data;

/**
 * Mtc - Manifacturers Taskcard. embedded object into {@link MaintenanceCard}
 */

@Data

public class Mtc {
    private String workArea;
    private String skill;

}
