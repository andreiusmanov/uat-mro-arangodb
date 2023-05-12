package uz.uat.mro.apps.model.common.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import com.arangodb.springframework.annotation.Relations.Direction;

import lombok.Data;
import uz.uat.mro.apps.model.activity.edge.MaintenanceType;
import uz.uat.mro.apps.model.marketing.entity.Project;

@Data
@Document("maintenance")
public class Maintenance {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private Integer code;
    @Relations(edges = { MaintenanceType.class }, lazy = false, direction = Direction.INBOUND)
    private List<Project> projects;

}
