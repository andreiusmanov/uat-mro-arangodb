package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.MpdTaskcard;

public interface MpdTaskcardsRepository extends ArangoRepository<MpdTaskcard,String>{
    
}
