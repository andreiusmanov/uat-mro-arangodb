package uz.uat.mro.apps.utils;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Set<String[]> set = new HashSet<>();
        List<MpdZone> zones = new ArrayList<>(0);
        MajorModel model = edition.getModel();
        try (Reader reader = Files.newBufferedReader(Path.of(filePath))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    set.add(line);
                }
            }
            set.stream().forEach(strings -> {
                MpdZone zone = new MpdZone(model);
                zone.setCode(strings[0]);
                zone.setName(strings[1]);
                zones.add(zone);
            });
            service.saveAllZones(zones);
        }
    }

    public static void importBoeingSubzones(MpdZonesService service, String filePath, MpdEdition edition)
            throws IOException, CsvValidationException {

        Set<String[]> list = new HashSet<>();
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

            list.stream().forEach(strings -> {
                MpdSubzone zone = new MpdSubzone(model);
                zone.setCode(strings[0]);
                zone.setName(strings[1]);
                zonesMap.getOrDefault(Integer.valueOf((zone.getCode().charAt(0)) * 100), null);
                subzones.add(zone);
            });
            service.saveAllSubzones(subzones);
        }
    }

    public static void importBoeingAccesses(MpdZonesService service, String accessesFile, MpdEdition edition)
            throws IOException, CsvValidationException {
        Set<MpdAccess> accesses = new HashSet<>(0);
        MajorModel model = edition.getModel();
        Map<String, MpdSubzone> subzonesMap = service.getAllSubzones(model.getArangoId());
        List<String[]> accessesArray = normalizeAccesses(accessesFile);
        accessesArray.stream().forEach(strings -> {
            MpdAccess access = new MpdAccess(model);
            access.setNumber(strings[0]);
            access.setOpen(new BigDecimal(strings[1].isBlank() ? "0.0" : strings[1]));
            access.setClose(new BigDecimal(strings[2].isBlank() ? "0.0" : strings[2]));
            access.setAplEngine(strings[3]);
            access.setName(strings[4]);
            subzonesMap.get(strings[5]);
            accesses.add(access);
        });
        service.saveAllAccesses(accesses);
    }

    public static void importBoeingAccessesSynth(MpdZonesService service, String syntheticAccessesFile,
            MpdEdition edition) throws IOException, CsvValidationException {
        Set<MpdAccess> accesses = new HashSet<>(0);

        MajorModel model = edition.getModel();
        Map<String, MpdSubzone> subzonesMap = service.getAllSubzones(model.getArangoId());

        List<String[]> synthAccessesArray = normalizeAccesses(syntheticAccessesFile);
        synthAccessesArray.stream().forEach(array -> {
            MpdAccess access2 = new MpdAccess(model);
            access2.setSynthetic(true);
            access2.setNumber(array[0]);
            access2.setOpen(new BigDecimal(array[1].isBlank() ? "0.0" : array[1]));
            access2.setClose(new BigDecimal(array[2].isBlank() ? "0.0" : array[2]));
            access2.setName(array[3]);
            access2.setMmReference(array[4]);
            subzonesMap.get(array[0].substring(0, 3));
            accesses.add(access2);
        });
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
