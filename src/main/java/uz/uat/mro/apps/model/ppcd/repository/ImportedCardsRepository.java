package uz.uat.mro.apps.model.ppcd.repository;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.ppcd.entity.ImportedCard;

public interface ImportedCardsRepository extends ArangoRepository<ImportedCard, String> {

    @Query(value = "for i in imported_cards filter i.revision == @revision return i")
    public Iterable<ImportedCard> findByRevision(@Param("revision") String revision);

}
