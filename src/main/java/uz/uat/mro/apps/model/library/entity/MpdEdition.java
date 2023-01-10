package uz.uat.mro.apps.model.library.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("mpd_editions")
public class MpdEdition {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String number;
    private String message;
    private LocalDate date;
    @Ref
    private MajorModel model;
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
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
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
        MpdEdition other = (MpdEdition) obj;
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
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
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
        return "MpdEdition [id=" + id + ", arangoId=" + arangoId + ", number=" + number + ", message=" + message
                + ", date=" + date + ", model=" + model + "]";
    }

}
