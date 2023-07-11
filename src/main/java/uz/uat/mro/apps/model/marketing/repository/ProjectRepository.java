package uz.uat.mro.apps.model.marketing.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.marketing.entity.Project;

public interface ProjectRepository extends ArangoRepository<Project, String> {
}
