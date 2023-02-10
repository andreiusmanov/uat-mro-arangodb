package uz.uat.mro.apps.model.library.entity;

import java.math.BigDecimal;

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
    private MpdEdition edition;
    @Ref
    private MpdItem mpdItem;
    private String mpdItemString;
    private String openMh;
    private String closeMh;
    private String totalMh;
    private String accessString;
}
