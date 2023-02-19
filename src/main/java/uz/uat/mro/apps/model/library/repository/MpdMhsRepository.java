package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdMh;

public interface MpdMhsRepository extends ArangoRepository<MpdMh, String> {

    @Query(value = "for i in mpd_mhs filter i.edition == @edition sort i.mpdItemString asc return i")
    public Iterable<MpdMh> findMhByEdition(String edition);
}
