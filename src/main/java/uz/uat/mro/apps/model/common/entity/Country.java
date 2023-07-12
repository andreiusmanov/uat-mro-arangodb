package uz.uat.mro.apps.model.common.entity;

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
    /*
     * id of the country 2 letter alpha code
     */
    @ArangoId
    private String arangoId;
    /* short customized name of the country */
    private String shortName;
    /*3 letters alpha numeric code of the country */
    private String code3;
    /* numeric code of the country */
    private String numeric;
    /* formal nam of the country */
    private String name;

}
