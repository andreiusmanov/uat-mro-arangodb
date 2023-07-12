package uz.uat.mro.apps.model.alt.activity;

import java.time.LocalDate;

import com.arangodb.entity.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.marketing.Project;

@Data
@Document(value = "package_revisions")
public class PackageRevision {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String number;
    @Ref(lazy = false)
    private Project project;
    private LocalDate date;
    private String description;
    private String status;
}
