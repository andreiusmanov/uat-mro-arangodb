package uz.uat.mro.apps.model.activity.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.entity.Project;

public interface ProjectRepository extends ArangoRepository<Project, String> {
}
