package uz.uat.mro.apps.model.activity.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.entity.MaintenanceArea;

public interface MaintenanceAreasRepository extends ArangoRepository<MaintenanceArea, String> {

}
