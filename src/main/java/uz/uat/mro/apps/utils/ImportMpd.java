package uz.uat.mro.apps.utils;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
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
        Map<String, MpdZone> zonesMap = service.getAllZones(model.getArangoId());

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
                zonesMap.getOrDefault(Integer.valueOf((zone.getCode().charAt(0)) * 100), null);
                subzones.add(zone);
            }
            service.saveAllSubzones(subzones);
        }
    }

    public static void importBoeingAccesses(MpdZonesService service, String accessesFile, String syntheticAccessesFile,
            MpdEdition edition) throws IOException, CsvValidationException {
        List<MpdAccess> accesses = new ArrayList<>(0);

        MajorModel model = edition.getModel();
        Map<String, MpdSubzone> subzonesMap = service.getAllSubzones(model.getArangoId());

        List<String[]> accessesArray = normalizeAccesses(accessesFile);

        for (String[] strings : accessesArray) {
            MpdAccess access = new MpdAccess(model);
            access.setNumber(strings[0]);
            access.setOpen(new BigDecimal(strings[1]));
            access.setClose(new BigDecimal(strings[2]));
            access.setAplEngine(strings[3]);
            access.setName(strings[4]);
            subzonesMap.get(strings[5]);
            accesses.add(access);
        }

        List<String[]> synthAccessesArray = normalizeAccesses(syntheticAccessesFile);
        for (String[] strings : synthAccessesArray) {
            MpdAccess access2 = new MpdAccess(model);
            access2.setNumber(strings[0]);
            access2.setOpen(new BigDecimal(strings[1]));
            access2.setClose(new BigDecimal(strings[2]));
            access2.setName(strings[3]);
            access2.setMmReference(strings[4]);
            subzonesMap.get(strings[0].substring(0, 3));
            accesses.add(access2);
        }
        service.saveAllAccesses(accesses);
    }

    private static List<String[]> normalizeAccesses(String fileName) throws IOException, CsvValidationException {
        try (Reader reader = Files.newBufferedReader(Path.of(fileName))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                String[] owner = new String[0];
                List<String[]> l = new ArrayList<>();

                while ((line = csvReader.readNext()) != null) {
                    if (!line[0].isEmpty()) {
                        owner = line;
                        l.add(owner);
                    } else {
                        for (int i = 0; i < line.length; i++) {
                            if (!line[i].isEmpty()) {
                                owner[i] = owner[i] + " " + line[i];
                            }
                        }
                    }
                }
                return l;
            }
        }
    }

    private static void importMpdItems() {

    }

    private static void importMpdTaskcards() {

    }

}
