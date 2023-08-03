package uz.uat.mro.apps.model.alt.marketing.repositories;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.alt.common.Maintenance;

public interface MaintenanceRepo extends ArangoRepository<Maintenance, String> {

    @Query(value = "for i in maintenances sort i.code, i.index asc return i")
    public List<Maintenance> findAllMaintenances();

    @Query(value = "for i in maintenances filter i.code == @code and i.index == @index sort i.code, i.index asc return i")
    public List<Maintenance> findAllMaintenances(String code, String index);

    @Query(value = "for i in maintenances filter i.code == @code sort i.code, i.index asc return i")
    public List<Maintenance> findAllMaintenances(String code);

}
