package uz.uat.mro.apps.model.library.service;

import org.springframework.stereotype.Service;

import uz.uat.mro.apps.model.library.repository.MpdAccessesRepository;
import uz.uat.mro.apps.model.library.repository.MpdSubzonesRepository;
import uz.uat.mro.apps.model.library.repository.MpdZonesRepository;

@Service
public class MpdImportService {
    private MpdZonesRepository zonesRepo;
    private MpdSubzonesRepository subzonesRepo;
    private MpdAccessesRepository accessesRepo;
    /**
     * @param zonesRepo
     * @param subzonesRepo
     * @param accessesRepo
     */
    public MpdImportService(MpdZonesRepository zonesRepo, MpdSubzonesRepository subzonesRepo,
            MpdAccessesRepository accessesRepo) {
        this.zonesRepo = zonesRepo;
        this.subzonesRepo = subzonesRepo;
        this.accessesRepo = accessesRepo;
    }

private void importZones(String fileName, int pageStart, int pageEnd){

}
private void importSubzones(String fileName, int pageStart, int pageEnd){

}

private void importAccesses(String fileName, int pageStart, int pageEnd){

}

private void importSyntheticAccesses(String fileName, int pageStart, int pageEnd){

}

private void importXlsMpdItems(String fileName){
    
}
private void importXlsMpdCards(String fileName){

}


}
