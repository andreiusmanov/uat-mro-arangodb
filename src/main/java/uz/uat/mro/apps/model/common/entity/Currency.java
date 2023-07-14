package uz.uat.mro.apps.model.common.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;

import lombok.Data;
import uz.uat.mro.apps.model.alt.common.Country;

@Data
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
