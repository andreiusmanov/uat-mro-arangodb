package uz.uat.mro.apps.model.alt.activity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.common.Uom;
import uz.uat.mro.apps.model.alt.marketing.Project;

@Data
@Document("material_items")
public class MaterialItem {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref
    private Project project;
    private String name;
    private String partNumber;
    private String quantity;
    private Uom uom;
    private String description;

}
