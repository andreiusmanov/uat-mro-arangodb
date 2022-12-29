package uz.uat.mro.apps.model.entity.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import uz.uat.mro.apps.model.entity.Firm;
import uz.uat.mro.apps.model.repository.FirmsRepository;

@Service
public class FirmsService {
    private final FirmsRepository repo;

    /**
     * @param repo
     */
    public FirmsService(FirmsRepository repo) {
        this.repo = repo;
    }

    public List<Firm> findFirms() {
        Iterable<Firm> findAll = repo.findAll();
        return StreamSupport.stream(findAll.spliterator(), false).toList();
    }

    public Firm save(Firm firm) {
        return repo.save(firm);
    }
    

}
