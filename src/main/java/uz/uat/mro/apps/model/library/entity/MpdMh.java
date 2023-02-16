package uz.uat.mro.apps.model.library.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("mpd_mhs")
public class MpdMh {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private MpdEdition edition;
    @Ref(lazy = false)
    private MpdItem mpdItem;
    private String mpdItemString;
    private String openMh;
    private String closeMh;
    private String totalMh;
    private String accessString;
}
