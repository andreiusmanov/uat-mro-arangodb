package uz.uat.mro.apps.model.activity.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.repository.MaintenanceTypeRepository;
import uz.uat.mro.apps.model.activity.repository.ProjectRepository;

@AllArgsConstructor
@Service
public class ProjectService {
    private ProjectRepository projectRepo;
    private MaintenanceTypeRepository linkRepo;

    public Project save(Project project) {
        return projectRepo.save(project);
    }

    public void delete(Project project) {
        projectRepo.delete(project);
    }

    public List<Project> findAll() {
        return StreamSupport.stream(projectRepo.findAll().spliterator(), false).toList();
    }

}
