package uz.uat.mro.apps.model.activity.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("section_codes")
public class SectionCode {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
}
