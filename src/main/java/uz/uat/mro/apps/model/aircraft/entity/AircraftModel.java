package uz.uat.mro.apps.model.aircraft.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("aircraft_models")
public class AircraftModel {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref
    private MajorModel majorModel;
    private String code;
    private String name;
    private String description;
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
        result = prime * result + ((majorModel == null) ? 0 : majorModel.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        AircraftModel other = (AircraftModel) obj;
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
        if (majorModel == null) {
            if (other.majorModel != null)
                return false;
        } else if (!majorModel.equals(other.majorModel))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
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
        return "AircraftModel [id=" + id + ", arangoId=" + arangoId + ", majorModel=" + majorModel + ", code=" + code
                + ", name=" + name + ", description=" + description + "]";
    }

}
