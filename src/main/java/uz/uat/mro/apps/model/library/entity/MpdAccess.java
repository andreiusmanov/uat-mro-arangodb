package uz.uat.mro.apps.model.library.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;

@Data
@Document("mpd_accesses")
public class MpdAccess {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private MpdSubzone subzone;
    @Ref(lazy = false)
    private MajorModel model;
    private String number;
    private BigDecimal open;
    private BigDecimal close;
    private String aplEngine;
    private String name;
    private Boolean synthetic;
    private String mmReference;
    private String subzoneString;

    public MpdAccess(MajorModel model) {
        this.model = model;
    }

    public MpdAccess(MajorModel model, String number) {
        this.model = model;
        this.number = number;
    }

}
