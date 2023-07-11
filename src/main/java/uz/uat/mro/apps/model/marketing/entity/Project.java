package uz.uat.mro.apps.model.marketing.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;
import com.arangodb.springframework.annotation.Relations.Direction;

import lombok.Data;
import uz.uat.mro.apps.model.activity.edge.MaintenanceType;
import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.model.common.entity.Maintenance;
import uz.uat.mro.apps.model.library.entity.MpdEdition;

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
    private Firm customer;
    @Ref(lazy = false)
    private Firm supplier;
    @Ref(lazy = false)
    private Aircraft aircraft;
    private String maintenanceString;
    @Relations(edges = { MaintenanceType.class }, lazy = false, direction = Direction.OUTBOUND)
    private List<Maintenance> maintenance;
    private String status;
    private Double coefficent;
}
