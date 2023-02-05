package uz.uat.mro.apps.model.library.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.library.entity.Ata100Chapter;
import uz.uat.mro.apps.model.library.repository.Ata100ChaptersRepository;

@AllArgsConstructor
@Service
public class Ata100ChaptersService {
    private Ata100ChaptersRepository repo;

    public Ata100Chapter save(Ata100Chapter entity) {
        return repo.save(entity);
    }

    public void delete(Ata100Chapter entity) {
        repo.delete(entity);
    }

    public List<Ata100Chapter> findAll() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }

}
