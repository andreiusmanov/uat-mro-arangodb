package uz.uat.mro.apps.model.services.aircraft;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.aircraft.AircraftAccess;
import uz.uat.mro.apps.model.alt.aircraft.AircraftSubzone;
import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftAccessRepo;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftSubzonesRepo;
import uz.uat.mro.apps.model.alt.aircraft.repositories.AircraftZonesRepo;

@Service
@AllArgsConstructor
public class AircraftZoneService {
    private AircraftZonesRepo aircraftZonesRepo;
    private AircraftSubzonesRepo aircraftSubzonesRepo;
    private AircraftAccessRepo aircraftAccessRepo;

    // AircraftZone

    public AircraftZone saveZone(AircraftZone aircraftZone) {
        return aircraftZonesRepo.save(aircraftZone);
    }

    public void saveAllZones(List<AircraftZone> aircraftZones) {
        aircraftZonesRepo.saveAll(aircraftZones);
    }

    public void deleteZone(AircraftZone aircraftZone) {
        aircraftZonesRepo.delete(aircraftZone);
    }

    public List<AircraftZone> getAllZonesByModel(MajorModel model) {
        return aircraftZonesRepo.findByModel(model);
    }

    // Aircraft Subzones

    public AircraftSubzone saveSubzone(AircraftSubzone aircraftSubzone) {
        return aircraftSubzonesRepo.save(aircraftSubzone);
    }

    public void saveAllSubzones(List<AircraftSubzone> aircraftSubzones) {
        aircraftSubzonesRepo.saveAll(aircraftSubzones);
    }

    public void deleteSubzone(AircraftSubzone aircraftSubzone) {
        aircraftSubzonesRepo.delete(aircraftSubzone);
    }

    public List<AircraftSubzone> getAllSubzonesByModel(MajorModel model) {
        return aircraftSubzonesRepo.findByModel(model);
    }

    // Aircraft Acceses

    public AircraftAccess saveAccess(AircraftAccess aircraftAccess) {
        return aircraftAccessRepo.save(aircraftAccess);
    }

    public void saveAllAccesses(List<AircraftAccess> aircraftAccesses) {
        aircraftAccessRepo.saveAll(aircraftAccesses);
    }

    public void deleteAccess(AircraftAccess aircraftAccess) {
        aircraftAccessRepo.delete(aircraftAccess);
    }

    public List<AircraftAccess> getAllAccessesBySubzone(AircraftSubzone subzone) {
        return aircraftAccessRepo.findBySubzone(subzone);
    }

}
