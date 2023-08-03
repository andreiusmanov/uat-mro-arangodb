package uz.uat.mro.apps.model.alt.marketing.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.apps.model.alt.common.Maintenance;
import uz.uat.mro.apps.model.alt.marketing.Project;

@Data
@Edge("project_maintenances")
public class ProjectMaintenance {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From(lazy = false)
    private Project project;
    @To(lazy = false)
    private Maintenance maintenance;
}
