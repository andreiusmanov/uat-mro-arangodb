package uz.uat.mro.apps.model.alt.library.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.library.MpdEdition;

public interface MpdEditionRepo extends ArangoRepository<MpdEdition, String> {

    @Query("FOR edition IN mpd_editions FILTER edition.model == @model RETURN edition")
    public List<MpdEdition> getEditionsByModel(@Param("model") String model);

}
