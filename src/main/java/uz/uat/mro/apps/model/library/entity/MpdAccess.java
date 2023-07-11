package uz.uat.mro.apps.model.library.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
