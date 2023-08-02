package uz.uat.mro.apps.model.alt.library;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.Data;
import uz.uat.mro.apps.model.alt.library.edges.MpdItemsAccesses;
import uz.uat.mro.apps.model.alt.library.edges.MpdItemsMhs;
import uz.uat.mro.apps.model.alt.library.edges.MpdItemsSubzones;
import uz.uat.mro.apps.model.alt.library.edges.MpdItemsTaskcards;

@Data
@Document("mpd_items")
public class MpdItem {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private MpdEdition edition;
    private String number;
    private String ammReference;
    private String cat;
    private String pgm;
    private String zone;
    private String task;
    private String access;
    private String threshold;
    private String repeat;
    private String apl;
    private String engine;
    private String mh;
    private String description;
    private String type;
    @Relations(edges = MpdItemsTaskcards.class, lazy = false)
    private List<MpdTaskcard> taskcards;
    @Relations(edges = MpdItemsMhs.class, lazy = false)
    private List<MpdMh> mhs;
    @Relations(edges = MpdItemsSubzones.class, lazy = false)
    private List<MpdTaskcard> zoneList;
    @Relations(edges = MpdItemsAccesses.class, lazy = false)
    private List<MpdMh> accesseList;

}
