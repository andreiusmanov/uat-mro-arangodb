package uz.uat.mro.apps.model.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.repository.MpdEditionsRepository;
import uz.uat.mro.apps.model.repositories.MajorModelRepo;

@AllArgsConstructor
@Service
public class MpdEditionService {
    private MpdEditionsRepository repo;
    private MajorModelRepo modelsRepo;

    public MpdEdition save(MpdEdition entity) {
        return repo.save(entity);
    }

    public void delete(MpdEdition entity) {
        repo.delete(entity);
    }

    public List<MpdEdition> findByMajorModel(MajorModel model) {
        if (model == null) {
            return new ArrayList<MpdEdition>(0);
        }
        return repo.findByMajorModel(model);
    }

    public List<MpdEdition> findAll() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }

    public List<MajorModel> findAllModels() {
        return StreamSupport.stream(modelsRepo.findAll().spliterator(), false).toList();
    }
}
