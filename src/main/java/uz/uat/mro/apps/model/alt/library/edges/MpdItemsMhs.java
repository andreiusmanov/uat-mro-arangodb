package uz.uat.mro.apps.model.alt.library.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;

import lombok.Data;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.alt.library.MpdMh;

@Data
@Edge("mpd_items_mhs")
public class MpdItemsMhs {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private MpdItem _from;
    private MpdMh _to;

}
