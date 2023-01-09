package uz.uat.mro.apps.model.aircraft.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.aircraft.repository.MajorModelsRepository;
import uz.uat.mro.apps.model.entity.Firm;

@AllArgsConstructor
@Service
public class MajorModelService {
    private MajorModelsRepository repo;

    public List<MajorModel> findByProdicer(Firm entity) {
        return repo.findByProducer(entity);
    }

    public MajorModel save(MajorModel entity) {
        return repo.save(entity);
    }

    public void delete(MajorModel entity) {
        repo.delete(entity);
    }

    public List<Firm> findProducers() {
        return repo.findProducers();
    }

public List<MajorModel> findAll(){
    Iterable<MajorModel> iterable = repo.findAll();
    return StreamSupport.stream(iterable.spliterator(),false).toList();
}


}
