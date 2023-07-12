package uz.uat.mro.apps.model.alt.activity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("maintenance_areas")
public class MaintenanceArea {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String code;
    private String name;


}
