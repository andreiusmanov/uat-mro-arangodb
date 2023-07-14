package uz.uat.mro.apps.model.services.common;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.common.Country;
import uz.uat.mro.apps.model.alt.common.Station;
import uz.uat.mro.apps.model.alt.common.repositories.StationsRepository;

@AllArgsConstructor
@Service

public class StationService {
    private final StationsRepository stationsRepository;
    private final CountryService countryService;

    public Station save(Station station) {
        return stationsRepository.save(station);
    }

    public void delete(Station station) {
        stationsRepository.delete(station);
    }

    public List<Station> findAllStations() {
        return StreamSupport.stream(stationsRepository.findAll().spliterator(), false).toList();
    }

    public List<Country> findAllCountries() {
        return StreamSupport.stream(countryService.findAll().spliterator(), false).toList();
    }
}
