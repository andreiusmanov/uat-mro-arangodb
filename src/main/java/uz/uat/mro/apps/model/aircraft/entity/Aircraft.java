package uz.uat.mro.apps.model.aircraft.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.uat.mro.apps.model.entity.Firm;

@Getter
@Setter
@AllArgsConstructor
@Document("aircraft")
public class Aircraft {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String regNumber;
    @Ref
    private Firm airline;
    @Ref
    private Firm owner;
    @Ref
    private AircraftModel model;

}
