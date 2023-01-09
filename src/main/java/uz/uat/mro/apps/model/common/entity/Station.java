package uz.uat.mro.apps.model.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Document("stations")
public class Station {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
    @Ref
    private Country country;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((arangoId == null) ? 0 : arangoId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
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
        Station other = (Station) obj;
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
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Station [id=" + id + ", arangoId=" + arangoId + ", name=" + name + ", code=" + code + ", country="
                + country + "]";
    }
}
