package uz.uat.mro.apps.model.library.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.library.MpdEdition;

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

}
