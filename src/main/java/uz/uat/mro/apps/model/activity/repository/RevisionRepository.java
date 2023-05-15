package uz.uat.mro.apps.model.activity.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.entity.Revision;

public interface RevisionRepository extends ArangoRepository<Revision, String> {
    // getZeroRevision

}
