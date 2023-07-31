package uz.uat.mro.apps.model.alt.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.library.MpdTaskcard;

public interface MpdTaskcardsRepo extends ArangoRepository<MpdTaskcard, String> {

    @Query(value = "for i in mpd_taskcards filter i.edition ==@edition return i")
    public List<MpdTaskcard> findCardsByEdition(@Param("edition") String edition);

    @Query(value = "for i in mpd_taskcards let a = (for m in mpd_items filter m.number == i.mpdItemString && m.edition == @edition return m) filter i.edition == @edition update i with {mpdItem:a} in mpd_taskcards")
    public void setMpdItems2Taskcards(@Param("edition") String edition);

}
