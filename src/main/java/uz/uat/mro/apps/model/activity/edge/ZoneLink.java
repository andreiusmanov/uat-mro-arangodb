package uz.uat.mro.apps.model.activity.edge;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.ppcd.entity.MaintenanceCard;

@Data
@Edge("zone_links")
public class ZoneLink {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private MaintenanceCard card;
    @To
    private MpdSubzone access;

}
