package uz.uat.mro.apps.model.library.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document("mpd_access_mhs")
public class MpdAccessMH {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref
    private MpdAccess access;
    @Ref
    private MpdEdition edition;
    private BigDecimal openMh;
    private BigDecimal closeMh;
    private BigDecimal totalMh;
}
