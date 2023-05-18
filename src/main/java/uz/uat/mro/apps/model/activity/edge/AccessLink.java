package uz.uat.mro.apps.model.activity.edge;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.ppcd.entity.MaintenanceCard;

@Data
@Edge("access_links")
public class AccessLink {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private MaintenanceCard card;
    @To
    private MpdAccess access;

    public AccessLink(MaintenanceCard card, MpdAccess access) {
        this.access = access;
        this.card = card;
    }
}
