package uz.uat.mro.apps.model.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndex;

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
    private Collection<Currency> currencies;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((arangoId == null) ? 0 : arangoId.hashCode());
        result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
        result = prime * result + ((code2 == null) ? 0 : code2.hashCode());
        result = prime * result + ((code3 == null) ? 0 : code3.hashCode());
        result = prime * result + ((numeric == null) ? 0 : numeric.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((currencies == null) ? 0 : currencies.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Country other = (Country) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (arangoId == null) {
            if (other.arangoId != null)
                return false;
        } else if (!arangoId.equals(other.arangoId))
            return false;
        if (shortName == null) {
            if (other.shortName != null)
                return false;
        } else if (!shortName.equals(other.shortName))
            return false;
        if (code2 == null) {
            if (other.code2 != null)
                return false;
        } else if (!code2.equals(other.code2))
            return false;
        if (code3 == null) {
            if (other.code3 != null)
                return false;
        } else if (!code3.equals(other.code3))
            return false;
        if (numeric == null) {
            if (other.numeric != null)
                return false;
        } else if (!numeric.equals(other.numeric))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (currencies == null) {
            if (other.currencies != null)
                return false;
        } else if (!currencies.equals(other.currencies))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Country [id=" + id + ", arangoId=" + arangoId + ", shortName=" + shortName + ", code2=" + code2
                + ", code3=" + code3 + ", numeric=" + numeric + ", name=" + name + ", currency=" + currencies + "]";
    }

}
