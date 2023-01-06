package uz.uat.mro.apps.model.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Edge("foreign")
public class ForegnKey {

    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private Sector sector;
    @To
    private Person person;
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((arangoId == null) ? 0 : arangoId.hashCode());
        result = prime * result + ((sector == null) ? 0 : sector.hashCode());
        result = prime * result + ((person == null) ? 0 : person.hashCode());
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
        ForegnKey other = (ForegnKey) obj;
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
        if (sector == null) {
            if (other.sector != null)
                return false;
        } else if (!sector.equals(other.sector))
            return false;
        if (person == null) {
            if (other.person != null)
                return false;
        } else if (!person.equals(other.person))
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
        return "ForegnKey [id=" + id + ", arangoId=" + arangoId + ", sector=" + sector + ", person=" + person + "]";
    }

}
