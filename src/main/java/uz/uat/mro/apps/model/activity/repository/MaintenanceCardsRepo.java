package uz.uat.mro.apps.model.activity.repository;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.apps.model.activity.service.LinkServiceClass;
import uz.uat.mro.apps.model.ppcd.entity.MaintenanceCard;

public interface MaintenanceCardsRepo extends ArangoRepository<MaintenanceCard, String> {

    @Query(value = "for i in maintenance_cards filter i.project == @project && i.status == 'active' return i")
    public Iterable<MaintenanceCard> findCardsByProject(@Param("project") String project);

    @Query(value = "for i in maintenance_cards filter i.project == @project && taskgroup == task_groups/routine && i.status == 'active' return i")
    public Iterable<MaintenanceCard> findRoutineCardsByProject(@Param("project") String project);

    @Query(value = "for i in maintenance_cards filter i.project == @project && taskgroup == task_groups/ht && i.status == 'active' return i")
    public Iterable<MaintenanceCard> findHardtimeCardsByProject(@Param("project") String project);

    @Query(value = "for i in maintenance_cards filter i.project == @project && taskgroup == task_groups/eo && i.status == 'active' return i")
    public Iterable<MaintenanceCard> findEoCardsByProject(@Param("project") String project);

    @Query(value = "for i in maintenance_cards filter i.project == @project && i.taskgroup == @type && i.status == 'active' return i")
    public Iterable<MaintenanceCard> findCardsByType(@Param("project") String project, @Param("type") String type);

    @Query(value = "for i in maintenance_cards for u in mpd_taskcards for it in mpd_items filter i.taskcard == u._id" +
            "&& u.mpdItem == it._id && i.project == @project && i.status == 'active' return {\"card\":i._id, \"accesses\":split(it.access,[\",\",\".0\",\", \",\"NOTE\"]), \"zones\":split(it.zone,[\",\",\".0\",\", \",\"NOTE\"])}")
    public Iterable<LinkServiceClass> getCards(@Param("project") String project);

}
