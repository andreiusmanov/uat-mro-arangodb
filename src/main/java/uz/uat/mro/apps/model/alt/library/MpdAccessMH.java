package uz.uat.mro.apps.model.alt.library;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.aircraft.AircraftAccess;

@Data
@Document("mpd_access_mhs")
public class MpdAccessMH {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref
    private AircraftAccess access;
    @Ref
    private MpdEdition edition;
    private BigDecimal openMh;
    private BigDecimal closeMh;
    private BigDecimal totalMh;
}
