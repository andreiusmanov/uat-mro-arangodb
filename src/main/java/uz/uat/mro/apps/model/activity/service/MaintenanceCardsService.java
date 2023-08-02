package uz.uat.mro.apps.model.activity.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.activity.entity.TaskGroup;
import uz.uat.mro.apps.model.activity.repository.MaintenanceCardsRepository;
import uz.uat.mro.apps.model.activity.repository.RevisionRepository;
import uz.uat.mro.apps.model.activity.repository.TaskGroupsRepository;
import uz.uat.mro.apps.model.alt.aircraft.AircraftAccess;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftAccessRepo;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftZonesRepo;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.alt.library.repository.MpdTaskcardsRepo;
import uz.uat.mro.apps.model.alt.marketing.Project;
import uz.uat.mro.apps.model.ppcd.entity.MaintenanceCard;

@AllArgsConstructor
@Service
public class MaintenanceCardsService {
    private MaintenanceCardsRepository cardsRepo;
    private TaskGroupsRepository taskgroupRepo;
    private MpdTaskcardsRepo taskcardsRepo;
    private AircraftAccessRepo accessesRepo;
    private AircraftZonesRepo zonesRepo;
    private RevisionRepository revisionRepo;

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
    public Optional<Revision> findRevision(String number, Project project) {
        Revision t = new Revision();
        t.setNumber(number);
        t.setProject(project);
        return revisionRepo.findOne(Example.of(t));
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

    public List<MaintenanceCard> findAllByProject(String project) {
        return StreamSupport.stream(cardsRepo.findCardsByProject(project).spliterator(), false).toList();
    }

    public List<MaintenanceCard> findAllByProjectActive(String project) {
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

    public List<AircraftAccess> findByModel(MajorModel model) {
        return accessesRepo.findByModel(model.getArangoId());
    }

    public Optional<AircraftAccess> getAccess(MajorModel model, String number) {
        AircraftAccess access = new AircraftAccess();
        access.setModel(model);
        access.setNumber(number);
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
                    AircraftAccess acc = service.getAccess(model, accesses[i]).get();
                    // AccessLink alink = new AccessLink()
                }
            }

        }

        );

    }

    public MaintenanceCard findMaintenanceCard(Project project, String sequence, String number) {

        MaintenanceCard m = new MaintenanceCard();
        m.setProject(project);
        m.setSequence(sequence);
        m.setNumber(number);
        return cardsRepo.findOne(Example.of(m)).get();
    }

}
