package uz.uat.mro.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.model.entities.common.Person;

public interface PersonRepo extends ArangoRepository<Person, String>{
    
}
