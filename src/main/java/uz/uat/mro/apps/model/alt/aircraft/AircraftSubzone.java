package uz.uat.mro.apps.model.alt.aircraft;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("aircraft_subzones")
public class AircraftSubzone {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String code;
    private String name;
    @Ref(lazy = false)
    private AircraftZone zone;
    @Ref(lazy = false)
    private MajorModel model;
}
