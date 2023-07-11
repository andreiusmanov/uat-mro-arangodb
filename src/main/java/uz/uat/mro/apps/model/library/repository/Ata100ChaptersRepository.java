package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.entity.AtaChapter;

public interface Ata100ChaptersRepository extends ArangoRepository<AtaChapter, String> {

}
