package uz.uat.mro.apps.model.common.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.common.entity.Uom;
import uz.uat.mro.apps.model.common.repository.UomRepository;

@AllArgsConstructor
@Service
public class UomService {
    private UomRepository repo;

    public Uom save(Uom entity) {
        return repo.save(entity);
    }

    public void delete(Uom entity) {
        repo.delete(entity);
    }

    public List<Uom> findAll() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }

}
