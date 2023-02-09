package uz.uat.mro.apps.model.library.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.Data;
import uz.uat.mro.apps.model.library.edges.RelatedTasks;

@Data
@Document("mpd_taskcards")

public class MpdTaskcard {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String number;
    @Ref
    private MpdItem mpdItems;
    private String mrbItem;
    private String task;
    private String title;
    @Relations(edges = RelatedTasks.class)
    private MpdTaskcard relatedTasks;

}
