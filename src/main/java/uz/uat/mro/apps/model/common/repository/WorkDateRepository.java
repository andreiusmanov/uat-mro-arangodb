package uz.uat.mro.apps.model.common.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.WorkDate;

public interface WorkDateRepository extends ArangoRepository<WorkDate, String> {

}
