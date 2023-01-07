package uz.uat.mro.apps.model.aircraft.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

@Document("major_models")
public class MajorModel {
    @Id
    private String id;
    @ArangoId
    private String arangoId;

    
}
