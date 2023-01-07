package uz.uat.mro.apps.model.aircraft.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("aircraft_models")
public class AircraftModel {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
}
