package uz.uat.mro.apps.model.alt.activity;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.marketing.Project;

@Data
@Document(value = "maintenance_taskcard_revision")
public class MaintenanceTaskcardRevision {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private Project project;
    private String item;
    @Ref(lazy = false)
    private PackageRevision revision;
    private String sequence;
    private String status;
    private String description;

}
