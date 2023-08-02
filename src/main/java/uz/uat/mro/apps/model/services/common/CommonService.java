package uz.uat.mro.apps.model.services.common;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.apps.model.alt.common.Currency;
import uz.uat.mro.apps.model.alt.common.Sector;
import uz.uat.mro.apps.model.alt.common.Uom;
import uz.uat.mro.apps.model.alt.common.WorkDate;
import uz.uat.mro.apps.model.alt.common.Workday;
import uz.uat.mro.apps.model.alt.common.repositories.CurrencyRepo;
import uz.uat.mro.apps.model.alt.common.repositories.SectorRepo;
import uz.uat.mro.apps.model.alt.common.repositories.UomRepo;
import uz.uat.mro.apps.model.alt.common.repositories.WorkdayRepo;

@Service
@AllArgsConstructor
public class CommonService {
    private UomRepo uomRepo;
    private CurrencyRepo currencyRepo;
    private SectorRepo sectorRepo;
    private WorkdayRepo workdayRepo;

    public List<WorkDate> saveAll(List<WorkDate> list) {
        return null;
    }

    // UOM
    public Uom saveUom(Uom uom) {
        return uomRepo.save(uom);
    }

    public void deleteUom(Uom uom) {
        uomRepo.delete(uom);
    }

    public List<Uom> findAllUom() {
        Iterable<Uom> uoms = uomRepo.findAll();
        return StreamSupport.stream(uoms.spliterator(), false).toList();
    }

    // Currencies

    public Currency saveCurrency(Currency currency) {
        return currencyRepo.save(currency);
    }

    public void deleteCurrency(Currency currency) {
        currencyRepo.delete(currency);
    }

    public List<Currency> findAllCurrencies() {
        Iterable<Currency> currencies = currencyRepo.findAll();
        return StreamSupport.stream(currencies.spliterator(), false).toList();
    }

    // sectors

    public Sector saveSector(Sector sector) {
        return sectorRepo.save(sector);
    }

    public void deleteSector(Sector sector) {
        sectorRepo.delete(sector);
    }

    public List<Sector> findAllSectors() {
        Iterable<Sector> sectors = sectorRepo.findAll();
        return StreamSupport.stream(sectors.spliterator(), false).toList();
    }

    public List<Sector> findByDepartmentId(String departmentId) {
        return sectorRepo.findByDepartmentId(departmentId);
    }

    // Workdays

    public Workday saveWorkDay(Workday workday) {
        return workdayRepo.save(workday);
    }

    public void deleteWorkDay(Workday workday) {
        workdayRepo.delete(workday);
    }

    public List<Workday> findAllWorkDays() {
        Iterable<Workday> workdays = workdayRepo.findAll();
        return StreamSupport.stream(workdays.spliterator(), false).toList();
    }

}
