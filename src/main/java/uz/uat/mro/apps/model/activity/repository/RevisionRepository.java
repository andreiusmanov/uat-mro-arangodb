package uz.uat.mro.apps.model.activity.repository;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.entity.Revision;

public interface RevisionRepository extends ArangoRepository<Revision, String> {

    @Query(value = "for i in package_revisions filter i.project == @project sort i.date return i")
    public Iterable<Revision> getRevisionsByProject(@Param("project") String project);
}
