package uz.uat.mro.apps.model.activity.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document("task_groups")
public class TaskGroup {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;

}
