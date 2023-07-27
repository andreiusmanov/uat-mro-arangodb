package uz.uat.mro.apps.model.library.repository;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.library.MpdMh;

public interface MpdMhsRepository extends ArangoRepository<MpdMh, String> {

    @Query("for i in mpd_mhs filter i.edition == @edition sort i.mpdItemString asc return i")
    public Iterable<MpdMh> findMhByEdition(String edition);

    @Query(value = "for card in mpd_taskcards for item in mpd_mhs filter card.edition ==@edition && item.edition == @edition && item.number == card.mpdItemString update card with {mpdItem:item._id} in mpd_mhs")
    public void setMpdItems2Mhs(@Param("edition") String edition);

}
