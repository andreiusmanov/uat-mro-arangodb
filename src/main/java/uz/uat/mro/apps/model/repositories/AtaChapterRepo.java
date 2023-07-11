package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.AtaChapter;

public interface AtaChapterRepo extends ArangoRepository<AtaChapter, String> {

}
