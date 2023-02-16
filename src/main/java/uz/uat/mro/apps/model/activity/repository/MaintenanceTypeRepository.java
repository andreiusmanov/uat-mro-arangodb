package uz.uat.mro.apps.model.activity.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.edge.MaintenanceType;

public interface MaintenanceTypeRepository extends ArangoRepository<MaintenanceType, String> {
    
}
