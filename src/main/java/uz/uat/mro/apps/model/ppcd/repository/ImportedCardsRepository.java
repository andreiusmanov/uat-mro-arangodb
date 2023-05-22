package uz.uat.mro.apps.model.ppcd.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.ppcd.entity.ImportedCard;

public interface ImportedCardsRepository extends ArangoRepository<ImportedCard, String> {

}
