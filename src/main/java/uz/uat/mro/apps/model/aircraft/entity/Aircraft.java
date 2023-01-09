package uz.uat.mro.apps.model.aircraft.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.uat.mro.apps.model.entity.Firm;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("aircrafts")
public class Aircraft {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String regNumber;
    @Ref
    private Firm airline;
    @Ref
    private Firm owner;
    @Ref
    private AircraftModel model;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((arangoId == null) ? 0 : arangoId.hashCode());
        result = prime * result + ((regNumber == null) ? 0 : regNumber.hashCode());
        result = prime * result + ((airline == null) ? 0 : airline.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
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
        Aircraft other = (Aircraft) obj;
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
        if (regNumber == null) {
            if (other.regNumber != null)
                return false;
        } else if (!regNumber.equals(other.regNumber))
            return false;
        if (airline == null) {
            if (other.airline != null)
                return false;
        } else if (!airline.equals(other.airline))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Aircraft [id=" + id + ", arangoId=" + arangoId + ", regNumber=" + regNumber + ", airline=" + airline
                + ", owner=" + owner + ", model=" + model + "]";
    }

}
