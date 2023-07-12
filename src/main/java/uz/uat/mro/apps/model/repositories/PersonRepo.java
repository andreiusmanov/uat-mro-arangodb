package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Person;

public interface PersonRepo extends ArangoRepository<Person, String>{
    
}
