package uz.uat.mro.apps.model.common.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Document("work_dates")
@Data
public class WorkDate {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String date;

}
