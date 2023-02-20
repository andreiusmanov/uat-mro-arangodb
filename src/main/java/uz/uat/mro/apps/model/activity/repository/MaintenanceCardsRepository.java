package uz.uat.mro.apps.model.activity.repository;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.entity.MaintenanceCard;

public interface MaintenanceCardsRepository extends ArangoRepository<MaintenanceCard, String> {

    @Query(value = "for i in maintenance_cards filter i.project == @project return i")
    public Iterable<MaintenanceCard> findCardsByProject(@Param("project") String project);

}
