package uz.uat.mro.apps.model.activity.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.entity.TaskGroup;

public interface TaskGroupsRepository extends ArangoRepository<TaskGroup, String> {

}
