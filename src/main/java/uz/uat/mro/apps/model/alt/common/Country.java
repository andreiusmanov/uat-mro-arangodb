package uz.uat.mro.apps.model.alt.common;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndex;

import lombok.Data;

@Data
@Document("countries")
@PersistentIndex(fields = { "code2" })
public class Country {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String shortName;
    private String code3;
    private String numeric;
    private String name;

}
