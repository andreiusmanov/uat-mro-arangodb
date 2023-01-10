package uz.uat.mro.apps.model.common.entity;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.Relations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Collection<Department> departments;

}
