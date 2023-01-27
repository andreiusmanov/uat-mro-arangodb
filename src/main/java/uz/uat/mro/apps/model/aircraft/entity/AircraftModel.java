package uz.uat.mro.apps.model.aircraft.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

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
