package uz.uat.mro.apps.model.alt.aircraft;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("aircraft_models")
public class AircraftModel {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref
    private MajorModel majorModel;
    private String code;
    private String name;
    private String description;
}
