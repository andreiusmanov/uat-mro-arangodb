package uz.uat.mro.apps.model.common.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.Maintenance;

public interface MaintenanceRepository extends ArangoRepository<Maintenance, String>{
    
}
