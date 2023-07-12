package uz.uat.mro.apps.model.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.Ata100Chapter;

public interface AtaChapterRepo extends ArangoRepository<Ata100Chapter, String> {

}
