package uz.uat.mro.apps.model.alt.common;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("workdays")
public class Workday {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private LocalDate date;
}
