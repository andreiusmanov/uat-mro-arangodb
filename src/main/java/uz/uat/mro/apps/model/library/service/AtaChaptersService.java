package uz.uat.mro.apps.model.library.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.library.entity.AtaChapter;
import uz.uat.mro.apps.model.library.repository.Ata100ChaptersRepository;

@AllArgsConstructor
@Service
public class AtaChaptersService {
    private Ata100ChaptersRepository repo;

    public AtaChapter save(AtaChapter entity) {
        return repo.save(entity);
    }

    public void delete(AtaChapter entity) {
        repo.delete(entity);
    }

    public List<AtaChapter> saveAll(List<AtaChapter> list) {
        return StreamSupport.stream(repo.saveAll(list).spliterator(), false).toList();
    }

    public List<AtaChapter> findAll() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }

}
