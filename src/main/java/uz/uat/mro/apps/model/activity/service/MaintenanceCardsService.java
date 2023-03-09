package uz.uat.mro.apps.model.activity.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.entity.MaintenanceCard;
import uz.uat.mro.apps.model.activity.entity.TaskGroup;
import uz.uat.mro.apps.model.activity.repository.MaintenanceCardsRepository;
import uz.uat.mro.apps.model.activity.repository.TaskGroupsRepository;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;
import uz.uat.mro.apps.model.library.repository.MpdTaskcardsRepository;

@AllArgsConstructor
@Service
public class MaintenanceCardsService {
    private MaintenanceCardsRepository cardsRepo;
    private TaskGroupsRepository taskgroupRepo;
    private MpdTaskcardsRepository taskcardsRepo;

    /**
     * collect task groups (eo, routine, ht)
     * 
     * @return
     */
    public List<TaskGroup> findAllTaskgroups() {
        return StreamSupport.stream(taskgroupRepo.findAll().spliterator(), false).toList();
    }

    /**
     * find mpd taskcard by number and edition
     * 
     * @param number
     * @param edition
     * @return
     */
    public MpdTaskcard findTaskcardByNumberAndEdition(String number, MpdEdition edition) {
        MpdTaskcard t = new MpdTaskcard();
        t.setEdition(edition);
        t.setNumber(number);
        return taskcardsRepo.findOne(Example.of(t)).get();
    }

    /**
     * save MaintenanceCard instance
     * 
     * @param card
     * @return
     */

    public MaintenanceCard save(MaintenanceCard card) {
        return cardsRepo.save(card);
    }

    /**
     * delete MaintenanceCard instance
     * 
     * @param card
     */
    public void delete(MaintenanceCard card) {
        cardsRepo.delete(card);
    }

    /**
     * find cards for the project
     * 
     * @param project
     * @return
     */
    public List<MaintenanceCard> findAllByProject(String project) {
        return StreamSupport.stream(cardsRepo.findCardsByProject(project).spliterator(), false).toList();
    }
    public List<MaintenanceCard> findRoutineByProject(String project) {
        return StreamSupport.stream(cardsRepo.findRoutineCardsByProject(project).spliterator(), false).toList();
    }
    public List<MaintenanceCard> findHtByProject(String project) {
        return StreamSupport.stream(cardsRepo.findHardtimeCardsByProject(project).spliterator(), false).toList();
    }
    public List<MaintenanceCard> findEoByProject(String project) {
        return StreamSupport.stream(cardsRepo.findEoCardsByProject(project).spliterator(), false).toList();
    }

    public List<MaintenanceCard> saveAll(List<MaintenanceCard> cards) {
        return StreamSupport.stream(cardsRepo.saveAll(cards).spliterator(), false).toList();
    }
}
