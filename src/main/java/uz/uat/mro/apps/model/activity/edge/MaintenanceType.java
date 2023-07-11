package uz.uat.mro.apps.model.activity.edge;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.apps.model.common.entity.Maintenance;
import uz.uat.mro.apps.model.marketing.entity.Project;

@Data
@Edge("maintenance_types")
public class MaintenanceType {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
@From
private Project project;
@To
private Maintenance maintenance;
}
