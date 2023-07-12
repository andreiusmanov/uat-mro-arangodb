package uz.uat.mro.apps.model.alt.nonroutine;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("acrs")
public class Acr {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
}
