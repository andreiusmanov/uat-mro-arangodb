package uz.uat.mro.apps.model.library.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdMh;

@Data
@Edge("related_accesses")
public class RelatedAccess {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @To
    private MpdAccess access;
    @From
    private MpdMh mh;
    
}
