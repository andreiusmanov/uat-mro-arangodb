package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.library.MpdTaskcard;

public interface MpdTaskcardsRepository extends ArangoRepository<MpdTaskcard, String> {

    @Query(value = "for i in mpd_taskcards filter i.edition ==@edition return i")
    public List<MpdTaskcard> findCardsByEdition(@Param("edition") String edition);

    @Query(value = "for card in mpd_taskcards for item in mpd_items filter card.edition ==@edition && item.edition == @edition && item.number == card.mpdItemString update card with {mpd_item:item._id} in mpd_taskcards")
    public void setMpdItems2Taskcards(@Param("edition") String edition);

}
