package uz.uat.mro.apps.model.alt.library.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;

import lombok.Data;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;

@Data
@Edge("mpd_items_taskcards")
public class MpdItemsTaskcards {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private MpdItem _from;
    private MpdTaskcard _to;
}
