package uz.uat.mro.apps.model.activity.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.arangodb.springframework.annotation.Query;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.repository.MaintenanceCardsRepository;
import uz.uat.mro.apps.model.activity.repository.MaintenanceTypeRepository;
import uz.uat.mro.apps.model.activity.repository.ProjectRepository;
import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.aircraft.repository.AircraftsRepository;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.model.common.repository.FirmsRepository;
import uz.uat.mro.apps.model.docs.MaintenanceCard;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.repository.MpdEditionsRepository;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepo;
    private MaintenanceTypeRepository linkRepo;
    private FirmsRepository firmRepo;
    private AircraftsRepository acRepo;
    private MaintenanceCardsRepository cardRepo;
    private MpdEditionsRepository editionRepo;

    public Project save(Project project) {
        return projectRepo.save(project);
    }

    public void delete(Project project) {
        projectRepo.delete(project);
    }

    public List<Project> findAll() {
        return StreamSupport.stream(projectRepo.findAll().spliterator(), false).toList();
    }

    public List<Firm> findAllCustomers() {
        return StreamSupport.stream(firmRepo.findAll().spliterator(), false).toList();
    }

    public List<Firm> findAllSuppliers() {
        return StreamSupport.stream(firmRepo.findAll().spliterator(), false).toList();
    }

    public List<Aircraft> findAllAircrafts() {
        return StreamSupport.stream(acRepo.findAll().spliterator(), false).toList();
    }

    public MaintenanceCard saveCard(MaintenanceCard card) {
        return cardRepo.save(card);
    }

    public void deleteCard(MaintenanceCard card) {
        cardRepo.delete(card);
    }

    public List<MaintenanceCard> findAllCards(String project) {
        return StreamSupport.stream(cardRepo.findCardsByProject(project).spliterator(), false).toList();
    }

    public List<MaintenanceCard> findRoutineCards(String project) {
        return StreamSupport.stream(cardRepo.findCardsByProject(project).spliterator(), false).toList();
    }

    public List<MaintenanceCard> findHtCards(String project) {
        return StreamSupport.stream(cardRepo.findCardsByProject(project).spliterator(), false).toList();
    }

    public List<MaintenanceCard> findEoCards(String project) {
        return StreamSupport.stream(cardRepo.findCardsByProject(project).spliterator(), false).toList();
    }

    public List<MpdEdition> findAllEditions() {
        return StreamSupport.stream(editionRepo.findAll().spliterator(), false).toList();
    }

}
