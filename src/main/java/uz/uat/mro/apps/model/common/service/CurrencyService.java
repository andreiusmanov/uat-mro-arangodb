package uz.uat.mro.apps.model.common.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.common.entity.Country;
import uz.uat.mro.apps.model.common.entity.Currency;
import uz.uat.mro.apps.model.common.repository.CountriesRepository;
import uz.uat.mro.apps.model.common.repository.CurrenciesRepository;

@AllArgsConstructor
@Service
public class CurrencyService {
    private CurrenciesRepository repo;
    private CountriesRepository countriesRepo;

    public Currency save(Currency entity) {
        return repo.save(entity);
    }

    public void delete(Currency entity) {
        repo.delete(entity);
    }

    public List<Currency> findAll() {
        Iterable<Currency> iterable = repo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public List<Country> findCountries() {
        Iterable<Country> iterable = countriesRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

}
