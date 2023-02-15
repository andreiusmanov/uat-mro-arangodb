package uz.uat.mro.apps.model.library.entity;

import java.util.Collection;

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
    @Ref
    private MpdEdition edition;
    private String number;
    @Ref
    private MpdItem mpdItem;
    private String mpdItemString;
    private String mrbItem;
    private String task;
    private String title;
    private String relatedTasksString;
    @Relations(edges = RelatedTasks.class)
    private Collection<MpdTaskcard> relatedTasks;

}
