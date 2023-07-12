package uz.uat.mro.apps.model.alt.common;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

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
    private Department department;
    //@Relations(edges = ForeignKey.class, lazy = false)
    private Collection<Person> staff;
    
   
}
