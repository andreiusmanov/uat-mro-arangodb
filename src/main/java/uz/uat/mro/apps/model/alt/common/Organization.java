package uz.uat.mro.apps.model.alt.common;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
public class Organization {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    @Ref
    private Country country;
    private String code;
    private String shortName;
    //@Relations(edges = OrganizedFirm.class, lazy = false)
    private Collection<Department> departments;

    }
