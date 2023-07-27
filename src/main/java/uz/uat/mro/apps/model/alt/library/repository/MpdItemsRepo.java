package uz.uat.mro.apps.model.alt.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdItem;

public interface MpdItemsRepo extends ArangoRepository<MpdItem, String> {

    @Query(value = "for i in mpd_items filter i.edition == @edition limit 5000 return i")
    public List<Object> findByEdition2(String edition);

    public List<MpdItem> findByEdition(String edition);

    @Query(value = "for i in mpd_items filter i.number == @number && i.edition == @edition return i")
    public List<MpdItem> findByNumberAndEdition(@Param("number") String number, @Param("edition") MpdEdition edition);

    @Query(value = "for i mpd_items for card in mpd_taskcards filter i.edition == @edition && card.mpdItem == i._id update i with {taskcards:item._id} in mpd_mhs")
    public void setMpdTaskcards2MpdItems(@Param("edition") String edition);
}
