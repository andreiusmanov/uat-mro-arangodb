package uz.uat.mro.apps.model.alt.marketing;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.aircraft.Aircraft;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.common.entity.Maintenance;

@Data
@Document("projects")
public class Project {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private MpdEdition edition;
    private String number;
    private LocalDate date;
    private LocalDate startDate;
    private LocalDate endDate;
    @Ref(lazy = false)
    private Organization customer;
    @Ref(lazy = false)
    private Organization supplier;
    @Ref(lazy = false)
    private Aircraft aircraft;
    private String maintenanceString;
  //  @Relations(edges = { MaintenanceType.class }, lazy = false, direction = Direction.OUTBOUND)
    private List<Maintenance> maintenance;
    private String status;
    private Double coefficent;
}
