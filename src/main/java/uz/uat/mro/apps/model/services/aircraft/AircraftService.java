package uz.uat.mro.apps.model.services.aircraft;

import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.aircraft.Aircraft;
import uz.uat.mro.apps.model.alt.aircraft.AircraftModel;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftModelRepo;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftRepo;
import uz.uat.mro.apps.model.alt.aircraft.repositories.MajorModelRepo;
import uz.uat.mro.apps.model.alt.organization.Organization;
import uz.uat.mro.apps.model.alt.organization.repositories.OrganizationRepo;

@Service
@AllArgsConstructor

public class AircraftService {
    private MajorModelRepo majorModelRepo;
    private AircraftModelRepo aircraftModelRepo;
    private AircraftRepo aircraftRepo;
    private OrganizationRepo organizationRepo;

    public MajorModel saveMajorModel(MajorModel majorModel) {
        return majorModelRepo.save(majorModel);
    }

    public MajorModel updateMajorModel(MajorModel majorModel) {
        return majorModelRepo.save(majorModel);
    }

    public void deleteMajorModel(MajorModel majorModel) {
        majorModelRepo.delete(majorModel);
    }

    public List<MajorModel> findAllMajorModels() {
        Iterable<MajorModel> iterable = majorModelRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public List<Organization> findAllOrganizations() {
        Iterable<Organization> iterable = organizationRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    // AircraftModel

    public AircraftModel saveAircraftModel(AircraftModel aircraftModel) {
        return aircraftModelRepo.save(aircraftModel);
    }

    public void deleteAircraftModel(AircraftModel aircraftModel) {
        aircraftModelRepo.delete(aircraftModel);
    }

    public List<AircraftModel> findAllAircraftModels() {
        Iterable<AircraftModel> iterable = aircraftModelRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    // Aircrafts

    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepo.save(aircraft);
    }

    public void deleteAircraft(Aircraft aircraft) {
        aircraftRepo.delete(aircraft);
    }

    public List<Aircraft> findAllAircrafts() {
        Iterable<Aircraft> iterable = aircraftRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }

    public List<Organization> findAirlineFirms() {
        Iterable<Organization> iterable = organizationRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).toList();
    }
}