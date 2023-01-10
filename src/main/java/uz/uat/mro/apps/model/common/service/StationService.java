package uz.uat.mro.apps.model.common.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.common.entity.Country;
import uz.uat.mro.apps.model.common.entity.Station;
import uz.uat.mro.apps.model.common.repository.CountriesRepository;
import uz.uat.mro.apps.model.common.repository.StationsRepository;

@AllArgsConstructor
@Service
public class StationService {
    private StationsRepository repo;
    private CountriesRepository countriesRepo;

    public List<Station> findStations() {
        Iterable<Station> findAll = repo.findAll();
        return StreamSupport.stream(findAll.spliterator(), false).toList();
    }

    public List<Station> findByCountry(Country country) {
        return repo.findByCountry(country);
    }

    public Station save(Station station) {
        return repo.save(station);
    }

    public void delete(Station station) {
        repo.delete(station);
    }

    public List<Country> findAllCountries() {
        return StreamSupport.stream(countriesRepo.findAll().spliterator(), false).toList();
    }

}
