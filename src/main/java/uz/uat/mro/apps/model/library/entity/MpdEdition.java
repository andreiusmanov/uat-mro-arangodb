package uz.uat.mro.apps.model.library.entity;

import java.time.LocalDate;

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
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
