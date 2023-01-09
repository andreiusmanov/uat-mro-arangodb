package uz.uat.mro.apps.model.common.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.Currency;

public interface CurrenciesRepository extends ArangoRepository<Currency, String> {

}
