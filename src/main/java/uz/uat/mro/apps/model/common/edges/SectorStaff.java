package uz.uat.mro.apps.model.common.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Edge("sector_staff")
public class SectorStaff {
    @Id
    private String id;
    @ArangoId
    private String arangoId;

}
