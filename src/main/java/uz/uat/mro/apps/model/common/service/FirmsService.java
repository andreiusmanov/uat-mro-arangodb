package uz.uat.mro.apps.model.common.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.common.entity.Country;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.model.common.repository.CountriesRepository;
import uz.uat.mro.apps.model.common.repository.FirmsRepository;

@AllArgsConstructor

@Service
public class FirmsService {
    private final FirmsRepository repo;
    private final CountriesRepository countriesRepo;

    public List<Firm> findFirms() {
        Iterable<Firm> findAll = repo.findAll();
        return StreamSupport.stream(findAll.spliterator(), false).toList();
    }

    public Firm save(Firm firm) {
        return repo.save(firm);
    }

    public void delete(Firm firm) {
        repo.delete(firm);
    }

    public List<Country> findAllCountries() {
        return StreamSupport.stream(countriesRepo.findAll().spliterator(), false).toList();
    }

}
