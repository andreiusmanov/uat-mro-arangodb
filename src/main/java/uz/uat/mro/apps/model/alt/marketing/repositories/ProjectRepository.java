package uz.uat.mro.apps.model.alt.marketing.repositories;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Maintenance;
import uz.uat.mro.apps.model.alt.marketing.Project;


public interface ProjectRepository extends ArangoRepository<Project, String> {

    @Query(value = "for i in maintenances  insert { _from: project, _to: i._id } into project_maintenances")
    public void saveMaintenance(List<Maintenance> items, String project);
}
