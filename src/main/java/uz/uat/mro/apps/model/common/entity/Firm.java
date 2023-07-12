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

@Data
@Document("firms")

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

}
