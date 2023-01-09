package uz.uat.mro.apps.model.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.entity.Country;
import uz.uat.mro.apps.model.repository.CountriesRepository;

@AllArgsConstructor
@Service
public class CountryService {
    private CountriesRepository repo;

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


}
