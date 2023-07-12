package uz.uat.mro.apps.model.alt.common;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("workday")
public class WorkDay {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private LocalDate date;
}
