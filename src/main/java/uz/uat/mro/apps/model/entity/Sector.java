package uz.uat.mro.apps.model.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Relations(edges = ForegnKey.class, lazy = false)
    private Person staff;
}
