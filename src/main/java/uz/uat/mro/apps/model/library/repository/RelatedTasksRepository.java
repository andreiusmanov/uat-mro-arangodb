package uz.uat.mro.apps.model.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.library.edges.RelatedTasks;

public interface RelatedTasksRepository extends ArangoRepository<RelatedTasks, String>{
}
