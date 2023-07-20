package uz.uat.mro.apps.model.common.entity;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.Relations;

import lombok.Data;
import uz.uat.mro.apps.model.alt.common.Department;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;

@Data
@Edge("works")
public class Works {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String position;
    private LocalDate start;
    private LocalDate end;
    @Relations(edges = Works.class, lazy = false)
    private Collection<OrganizationUnit> departments;

}
