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
@Document("currencies")
@PersistentIndex(fields = { "code" })
public class Currency {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String code;
    private String numeric;
    @Relations(edges = LinkCountry.class, lazy = true)
    private Collection<Country> countries;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((arangoId == null) ? 0 : arangoId.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((numeric == null) ? 0 : numeric.hashCode());
        result = prime * result + ((countries == null) ? 0 : countries.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Currency other = (Currency) obj;
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
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (numeric == null) {
            if (other.numeric != null)
                return false;
        } else if (!numeric.equals(other.numeric))
            return false;
        if (countries == null) {
            if (other.countries != null)
                return false;
        } else if (!countries.equals(other.countries))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Currency [id=" + id + ", arangoId=" + arangoId + ", code=" + code + ", numeric=" + numeric
                + ", countries=" + countries + "]";
    }

}
