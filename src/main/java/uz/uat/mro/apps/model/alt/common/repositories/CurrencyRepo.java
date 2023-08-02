package uz.uat.mro.apps.model.alt.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Currency;

public interface CurrencyRepo extends ArangoRepository<Currency, String>{
    
}
