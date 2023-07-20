package uz.uat.mro.apps.model.common.entity;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;

@Data
@Document("sectors")

public class Sector {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
    private String shortName;
    @Ref
    private OrganizationUnit department;
    @Relations(edges = ForeignKey.class, lazy = false)
    private Collection<Person> staff;
    
}
