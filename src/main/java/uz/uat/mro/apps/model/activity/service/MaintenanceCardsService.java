package uz.uat.mro.apps.model.activity.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.entity.TaskGroup;
import uz.uat.mro.apps.model.activity.repository.MaintenanceCardsRepository;
import uz.uat.mro.apps.model.activity.repository.TaskGroupsRepository;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.docs.MaintenanceCard;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;
import uz.uat.mro.apps.model.library.repository.MpdAccessesRepository;
import uz.uat.mro.apps.model.library.repository.MpdTaskcardsRepository;
import uz.uat.mro.apps.model.marketing.entity.Project;

@AllArgsConstructor
@Service
public class MaintenanceCardsService {
    private MaintenanceCardsRepository cardsRepo;
    private TaskGroupsRepository taskgroupRepo;
    private MpdTaskcardsRepository taskcardsRepo;
    private MpdAccessesRepository accessesRepo;
    private MpdAccessesRepository zonesRepo;

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
    public Optional<MpdTaskcard> findTaskcardByNumberAndEdition(String number, MpdEdition edition) {
        MpdTaskcard t = new MpdTaskcard();
        t.setEdition(edition);
        t.setNumber(number);
        return taskcardsRepo.findOne(Example.of(t));
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

    public List<MaintenanceCard> findMaintenanceCardByNumber(String number, Project project) {
        MaintenanceCard mc = new MaintenanceCard();
        mc.setProject(project);
        mc.setTaskcardString(number);
        return StreamSupport.stream(cardsRepo.findAll(Example.of(mc)).spliterator(), false).toList();
    };

    public List<MpdAccess> findByModel(String model) {
        return accessesRepo.findByModel(model);
    }

    public Optional<MpdAccess> getAccess(MajorModel model, String number) {
        MpdAccess access = new MpdAccess(model, number);
        return accessesRepo.findOne(Example.of(access));
    }

    public void getCards(MaintenanceCardsService service, Project project, MpdEdition edition) {

        MajorModel model = edition.getModel();
        List<LinkServiceClass> list = StreamSupport
                .stream(cardsRepo.getCards(project.getArangoId()).spliterator(), false).toList();
        list.stream().forEach(doc -> {
            String[] accesses = doc.getAccesses();
            String[] zones = doc.getZones();

            for (int i = 0; i < accesses.length; i++) {
                if (!accesses[i].isBlank()) {
                    MpdAccess acc = service.getAccess(model, accesses[i]).get();
                    // AccessLink alink = new AccessLink()
                }
            }

        }

        );

    }

}
