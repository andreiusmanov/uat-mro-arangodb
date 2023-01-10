package uz.uat.mro.apps.model.aircraft.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.aircraft.entity.Aircraft;
import uz.uat.mro.apps.model.aircraft.entity.AircraftModel;
import uz.uat.mro.apps.model.aircraft.repository.AircraftModelsRepository;
import uz.uat.mro.apps.model.aircraft.repository.AircraftsRepository;
import uz.uat.mro.apps.model.common.entity.Firm;
import uz.uat.mro.apps.model.common.repository.FirmsRepository;

@AllArgsConstructor
@Service
public class AircraftService {
    private AircraftsRepository repo;
    private AircraftModelsRepository modelsRepo;
    private FirmsRepository firmsRepo;

    public Aircraft save(Aircraft entity) {
        return repo.save(entity);
    }

    public void delete(Aircraft entity) {
        repo.delete(entity);
    }

    public Aircraft findByRegNumber(String regNumber) {
        return repo.findByRegNumber(regNumber);
    }

    public List<Aircraft> findByAirline(Firm airline) {
        return repo.findByAirline(airline);
    }

    public List<Aircraft> findByOwner(Firm owner) {
        return repo.findByOwner(owner);
    }

    public List<Aircraft> findByModel(AircraftModel model) {
        return repo.findByModel(model);
    }

    public List<Aircraft> findAll() {
        Iterable<Aircraft> iterable = repo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public List<AircraftModel> findModels() {
        Iterable<AircraftModel> iterable = modelsRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public List<Firm> findAirlines() {
        Iterable<Firm> iterable = firmsRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public List<Firm> findOwners() {
        Iterable<Firm> iterable = firmsRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public List<Firm> findAirlineFirms() {
        return firmsRepo.findFirms();
    }

    public List<Firm> findOwnerFirms() {
        return firmsRepo.findFirms();
    }
}
