package uz.uat.mro.apps.model.services.common;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.common.Country;
import uz.uat.mro.apps.model.alt.common.repositories.CountriesRepo;

@AllArgsConstructor
@Service
public class CountryService {
    private CountriesRepo repo;

    public Country save(Country entity) {
        return repo.save(entity);
    }

    public void delete(Country entity) {
        repo.delete(entity);
    }

    public List<Country> findAll() {
        Iterable<Country> iterable = repo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public Country findById(String id) {
        return repo.findById(id).get();
    }

    public Country findByShortName(String shortName) {
        return repo.findByShortName(shortName);
    }

}
