package uz.uat.mro.apps.model.activity.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.activity.entity.Revision;
import uz.uat.mro.apps.model.activity.repository.RevisionRepository;

@Service
@AllArgsConstructor
public class RevisionService {
    private RevisionRepository repo;

    public Revision save(Revision entity) {
        return repo.save(entity);
    }

    public void delete(Revision entity) {
        repo.delete(entity);
    }

    public List<Revision> getRevisionsByProject(String project) {
        return StreamSupport.stream(repo.getRevisionsByProject(project).spliterator(), false).toList();
    }

}
