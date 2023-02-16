package uz.uat.mro.apps.model.activity.entity;

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

@Data
@Document("projects")
public class Project {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String number;
    private LocalDate date;
    private LocalDate startDate;
    private LocalDate endDate;
    @Ref
    private Firm customer;
    @Ref
    private Firm supplier;
    @Ref
    private Aircraft aircraft;
    private String maintenanceString;
    @Relations(edges = {MaintenanceType.class}, lazy = false, direction = Direction.OUTBOUND)
    private List<Maintenance> maintenance;


}
