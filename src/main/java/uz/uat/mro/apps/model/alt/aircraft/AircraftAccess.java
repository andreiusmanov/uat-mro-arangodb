package uz.uat.mro.apps.model.alt.aircraft;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("aircraft_accesses")
public class AircraftAccess {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private AircraftSubzone subzone;
    @Ref(lazy = false)
    private MajorModel model;
    private String number;
    private BigDecimal open;
    private BigDecimal close;
    private String aplEngine;
    private String name;
    private Boolean synthetic;
    private String mmReference;
    private String subzoneString;

}
