package uz.uat.mro.apps.model.common.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.PersistentIndex;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document("currencies")
public class Currency {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String numeric;
    private String name;
    @Relations(edges = ForeignKey.class, lazy = false)
    private List<Country> countries;
}
