package uz.uat.mro.apps.model.alt.library;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;

@Data
@Document("mpd_editions")
public class MpdEdition {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String number;
    private String message;
    private LocalDate date;
    @Ref
    private MajorModel model;

}
