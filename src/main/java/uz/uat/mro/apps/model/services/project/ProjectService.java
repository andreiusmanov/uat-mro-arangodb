package uz.uat.mro.apps.model.services.project;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.repository.MaintenanceCardsRepository;
import uz.uat.mro.apps.model.alt.aircraft.Aircraft;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftRepo;
import uz.uat.mro.apps.model.alt.common.Maintenance;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.repository.MpdEditionRepo;
import uz.uat.mro.apps.model.alt.marketing.Project;
import uz.uat.mro.apps.model.alt.marketing.repositories.MaintenanceRepo;
import uz.uat.mro.apps.model.alt.marketing.repositories.ProjectRepository;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.alt.organization.repositories.OrganizationRepo;
import uz.uat.mro.apps.model.ppcd.entity.MaintenanceCard;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepo;
    private OrganizationRepo firmRepo;
    private AircraftRepo acRepo;
    private MaintenanceCardsRepository cardRepo;
    private MpdEditionRepo editionRepo;
    private MaintenanceRepo maintenanceRepo;

    public Project save(Project project) {
        return projectRepo.save(project);
    }

    public void delete(Project project) {
        projectRepo.delete(project);
    }

    public List<Project> findAll() {
        return StreamSupport.stream(projectRepo.findAll().spliterator(), false).toList();
    }

    public List<Organization> findAllCustomers() {
        return StreamSupport.stream(firmRepo.findAll().spliterator(), false).toList();
    }

    public List<Organization> findAllSuppliers() {
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

    // Project

    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    public Project saveMaintenance(Project project) {
        Project p = saveProject(project);
        projectRepo.saveMaintenance(p.getMaintenance(), p.getId());
        return p;
    }

    public List<Maintenance> findAllMaintenances() {
        return StreamSupport.stream(maintenanceRepo.findAllMaintenances().spliterator(), false).toList();
    }
}
