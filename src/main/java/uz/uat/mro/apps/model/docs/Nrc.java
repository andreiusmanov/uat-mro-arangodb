package uz.uat.mro.apps.model.docs;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("nrcs")
public class Nrc {
    @Id
    private String id;
    @ArangoId 
    private String arangoId;
}
