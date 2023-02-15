package uz.uat.mro.apps.model.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.entity.MpdItem;

public interface MpdItemsRepository extends ArangoRepository<MpdItem, String> {

    List<MpdItem> findByEdition(String edition);

    @Query(value = "for i in mpd_items filter i.number == @number && i.edition == @edition return i")
    List<MpdItem> findByNumberAndEdition(@Param("number") String number, @Param("edition") MpdEdition edition);

    @Query(value = "for i in mpd_items filter i.edition == @edition return i")
    List<MpdItem> getMpdItems(@Param("edition") String edition);

}
