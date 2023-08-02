package uz.uat.mro.apps.model.alt.marketing.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.marketing.Project;


public interface ProjectRepository extends ArangoRepository<Project, String> {
}
