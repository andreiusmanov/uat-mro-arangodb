package uz.uat.mro.apps.model.alt.organization;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.Data;
import uz.uat.mro.apps.model.alt.common.Country;
import uz.uat.mro.apps.model.alt.organization.edges.HasUnit;

@Data
@Document("organizations")
public class Organization {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    @Ref(lazy = true)
    private Country country;
    private String code;
    private String shortName;
    @Relations(edges = HasUnit.class, lazy = false)
    private Collection<OrganizationUnit> departments;

}
