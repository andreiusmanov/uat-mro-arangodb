package uz.uat.mro.apps.model.activity.repository;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.service.LinkServiceClass;
import uz.uat.mro.apps.model.docs.MaintenanceCard;

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


    @Query(value = "for i in maintenance_cards for u in mpd_taskcards for it in mpd_items filter i.taskcard == u._id" +
    "&& u.mpdItem == it._id && i.project == @project return {\"card\":i._id, \"accesses\":split(it.access,[\",\",\".0\",\", \",\"NOTE\"]), \"zones\":split(it.zone,[\",\",\".0\",\", \",\"NOTE\"])}")
    public Iterable<LinkServiceClass> getCards(@Param("project") String project);

}
