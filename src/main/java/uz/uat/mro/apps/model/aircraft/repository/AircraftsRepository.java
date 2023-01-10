package uz.uat.mro.apps.model.aircraft.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.aircraft.entity.AircraftModel;
import uz.uat.mro.apps.model.common.entity.Firm;

public interface AircraftsRepository extends ArangoRepository<Aircraft, String> {

    @Query(value = "for i in aircrafts filter i.reg_number = @regNumber return i")
    public Aircraft findByRegNumber(@Param("regNumber") String regNumber);

    @Query(value = "for i in aircrafts filter i.airline = @airline return i")
    public List<Aircraft> findByAirline(@Param("firm") Firm airline);

    @Query(value = "for i in aircrafts filter i.owner = @owner return i")
    public List<Aircraft> findByOwner(@Param("owner") Firm owner);
    
    @Query(value = "for i in aircrafts filter i.model = @model return i")
    public List<Aircraft> findByModel(@Param("model") AircraftModel model);

}
