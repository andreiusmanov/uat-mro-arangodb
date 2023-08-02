package uz.uat.mro.apps.model.alt.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Workday;

public interface WorkdayRepo extends ArangoRepository<Workday, String> {

}
