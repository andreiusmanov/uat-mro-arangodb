package uz.uat.mro.apps.utils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.service.MpdZonesService;

public class ImportMpd {

    public static void importBoeingZones(MpdZonesService service, String filePath, MpdEdition edition)
            throws IOException, CsvValidationException {
        List<String[]> list = new ArrayList<>();
        List<MpdZone> zones = new ArrayList<>(0);
        MajorModel model = edition.getModel();
        try (Reader reader = Files.newBufferedReader(Path.of(filePath))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
            for (String[] strings : list) {
                MpdZone zone = new MpdZone(model);
                zone.setCode(strings[0]);
                zone.setName(strings[1]);
                zones.add(zone);
            }
            service.saveAllZones(zones);
        }
    }

    public static void importBoeingSubzones(MpdZonesService service, String filePath, MpdEdition edition)
            throws IOException, CsvValidationException {
                
        List<String[]> list = new ArrayList<>();
        List<MpdSubzone> subzones = new ArrayList<>(0);

        MajorModel model = edition.getModel();
        Map<String,MpdZone> zonesMap = service.getAllZones(model.getArangoId());
        
        try (Reader reader = Files.newBufferedReader(Path.of(filePath))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
            for (String[] strings : list) {
                MpdSubzone zone = new MpdSubzone(model);
                zone.setCode(strings[0]);
                zone.setName(strings[1]);
               Integer zId = Integer.valueOf(zone.getCode());
               
               
               
                subzones.add(zone);
            }
            service.saveAllZones(subzones);
        }
    }

}
