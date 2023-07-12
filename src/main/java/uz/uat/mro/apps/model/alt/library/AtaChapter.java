package uz.uat.mro.apps.model.alt.library;


import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("ata_chapters")

public class AtaChapter {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String general;
    private String number;
    private String name;

}
