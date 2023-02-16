package uz.uat.mro.apps.model.library.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Edge("related_tasks")
public class RelatedTasks {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @To
    private MpdTaskcard toCard;
    @From
    private MpdTaskcard fromCard;
    private String relation;

}
