package uz.uat.mro.apps.model.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Currency;

public interface CurrenciesRepository extends ArangoRepository<Currency, String> {

}
