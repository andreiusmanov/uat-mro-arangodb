package uz.uat.mro.apps.model.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.WorkDay;

public interface WorkDayRepository extends ArangoRepository<WorkDay, String> {

}
