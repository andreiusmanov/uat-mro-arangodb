package uz.uat.mro.apps.model.activity.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.entity.MaintenanceCard;

public interface MaintenanceCardsRepository extends ArangoRepository<MaintenanceCard, String> {

    @Query(value = "for i in maintenance_cards filter i.project == @project return i")
    public Iterable<MaintenanceCard> findCardsByProject(@Param("project") String project);
    
    @Query(value = "for i in maintenance_cards filter i.project == @project && taskgroup == task_groups/routine return i")
    public Iterable<MaintenanceCard> findRoutineCardsByProject(@Param("project") String project);
    
    @Query(value = "for i in maintenance_cards filter i.project == @project && taskgroup == task_groups/ht return i")
    public Iterable<MaintenanceCard> findHardtimeCardsByProject(@Param("project") String project);
    
    @Query(value = "for i in maintenance_cards filter i.project == @project && taskgroup == task_groups/eo return i")
    public Iterable<MaintenanceCard> findEoCardsByProject(@Param("project") String project);
    
    @Query(value = "for i in maintenance_cards filter i.project == @project && i. return i")
    public Iterable<MaintenanceCard> findCardsByType(@Param("project") String project, @Param("type") String type);


    
}
