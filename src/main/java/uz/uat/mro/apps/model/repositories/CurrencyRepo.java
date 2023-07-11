package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.Currency;

public interface CurrencyRepo extends ArangoRepository<Currency, String>{
    
}
