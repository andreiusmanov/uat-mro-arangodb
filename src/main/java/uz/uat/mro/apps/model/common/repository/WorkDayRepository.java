package uz.uat.mro.apps.model.common.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.common.entity.WorkDay;

public interface WorkDayRepository extends ArangoRepository<WorkDay, String> {

}
