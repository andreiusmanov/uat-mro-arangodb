package uz.uat.mro.apps.model.repository;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.entity.Firm;

public interface FirmsRepository extends ArangoRepository<Firm, String> {

    @Query(value = "for i in firms return i")
    public List<Firm> findFirms();
}
