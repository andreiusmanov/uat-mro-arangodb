package uz.uat.mro.apps.model.common.entity;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.uat.mro.apps.model.alt.organization.OrganizationUnit;

@Data
@Document("persons")
public class Person {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String surname;
    private String patronymic;
    private String passportName;
    private String fio;
    private String shortFio;
    private LocalDate dob;
    @Relations(edges = Works.class, lazy = false)
    private Collection<OrganizationUnit> departments;

}
