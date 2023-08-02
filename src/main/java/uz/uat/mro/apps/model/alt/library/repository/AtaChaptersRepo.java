package uz.uat.mro.apps.model.alt.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.library.AtaChapter;

public interface AtaChaptersRepo extends ArangoRepository<AtaChapter, String>{
    
}
