package uz.uat.mro.apps.model.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("firms")
// @PersistentIndex(fields = { "code2" })

public class Firm {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    @Ref
    private Country country;
    private String code;
    private String shortName;
    @Relations(edges = OrganizedFirm.class, lazy = false)
    private Collection<Department> departments;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((arangoId == null) ? 0 : arangoId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
        result = prime * result + ((departments == null) ? 0 : departments.hashCode());
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
        Firm other = (Firm) obj;
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (shortName == null) {
            if (other.shortName != null)
                return false;
        } else if (!shortName.equals(other.shortName))
            return false;
        if (departments == null) {
            if (other.departments != null)
                return false;
        } else if (!departments.equals(other.departments))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Firm [id=" + id + ", arangoId=" + arangoId + ", name=" + name + ", country=" + country + ", code="
                + code + ", shortName=" + shortName + ", departments=" + departments + "]";
    }

}
