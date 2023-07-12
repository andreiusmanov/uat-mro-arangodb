package uz.uat.mro.apps.model.alt.activity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

/*
 * workcard types - routine, hard time, engineering order
 */
@Data
@Document("workcard_types")
public class WorkcardType {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
}
