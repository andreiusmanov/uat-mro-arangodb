package uz.uat.mro.apps.model.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndex;
import com.arangodb.springframework.annotation.Relations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("countries")
@PersistentIndex(fields = { "code2" })
public class Country {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String shortName;
    private String code2;
    private String code3;
    private String numeric;
    private String name;
    @Relations(edges = LinkCountry.class, lazy = true)
    private Collection<Currency> currency;
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Country [id=" + id + ", arangoId=" + arangoId + ", shortName=" + shortName + ", code2=" + code2
                + ", code3=" + code3 + ", numeric=" + numeric + ", name=" + name + ", currency=" + currency + "]";
    }

}
