package uz.uat.mro.apps.model.library.entity;

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
@Document("mpd_zones")

public class MpdZone {
    @Id
    private String id;
    @ArangoId
    private String arangoId;

    private String code;
    private String name;
    @Ref
    private MajorModel model;

    /**
     * @param model
     */
    public MpdZone(MajorModel model) {
        this.model = model;
    }

}
