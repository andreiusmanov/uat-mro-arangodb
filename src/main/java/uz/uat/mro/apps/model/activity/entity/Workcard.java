package uz.uat.mro.apps.model.activity.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;

@Data
@Document("workcards")
public class Workcard {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String number;
    @Ref
    private TaskGroup taskGroup;
    @Ref
    private SectionCode sectionCode;
    @Ref
    private Project project;
    @Ref
    private MpdTaskcard taskcard;
    private String mpReference;
    private String description;
    private String remarks;

}
