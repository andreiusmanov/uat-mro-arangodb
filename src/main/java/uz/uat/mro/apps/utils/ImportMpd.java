package uz.uat.mro.apps.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import uz.uat.mro.apps.model.aircraft.entity.MajorModel;
import uz.uat.mro.apps.model.library.entity.MpdAccess;
import uz.uat.mro.apps.model.library.entity.MpdEdition;
import uz.uat.mro.apps.model.library.entity.MpdItem;
import uz.uat.mro.apps.model.library.entity.MpdMh;
import uz.uat.mro.apps.model.library.entity.MpdSubzone;
import uz.uat.mro.apps.model.library.entity.MpdTaskcard;
import uz.uat.mro.apps.model.library.entity.MpdZone;
import uz.uat.mro.apps.model.library.service.DataImportService;

public class ImportMpd {

    public static void importBoeingZones(DataImportService service, String fileName, MpdEdition edition)
            throws IOException, CsvValidationException {
        if (fileName.isBlank()) {
            return;
        }
        Set<String[]> set = new HashSet<>();
        Set<MpdZone> zones = new HashSet<>(0);
        MajorModel model = edition.getModel();
        try (Reader reader = Files.newBufferedReader(Path.of(fileName))) {
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
                zone.setModel(model);
                zones.add(zone);
            });
            service.saveAllZones(zones);
        }
    }

    public static void importBoeingSubzones(DataImportService service, String filePath, MpdEdition edition)
            throws IOException, CsvValidationException {

        if (filePath.isBlank()) {
            return;
        }
        Set<String[]> list = new HashSet<>();
        Set<MpdSubzone> subzones = new HashSet<>(0);

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
                MpdSubzone subzone = new MpdSubzone(model);
                subzone.setCode(strings[0]);
                subzone.setName(strings[1]);
                char s = subzone.getCode().charAt(0);
                String res = s + "00";
                subzone.setZone(zonesMap.getOrDefault(res, null));
                System.out.println(subzone.getCode() + ", " + subzone.getName() + ", "
                        + subzone.getZone().getArangoId() + ", " + subzone.getModel().getArangoId());
                subzones.add(subzone);
            });
            service.saveAllSubzones(subzones);
        }
    }

    public static void importBoeingAccesses(DataImportService service, String accessesFile, MpdEdition edition)
            throws IOException, CsvValidationException {
        if (accessesFile.isBlank()) {
            return;
        }
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

    public static void importBoeingAccessesSynth(DataImportService service, String syntheticAccessesFile,
            MpdEdition edition) throws IOException, CsvValidationException {
        if (syntheticAccessesFile.isBlank()) {
            return;
        }
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

    public static void importMpdItems(DataImportService service, String fileName, String[] sheetNames,
            MpdEdition edition)
            throws FileNotFoundException, IOException {
        if (fileName.isBlank()) {
            return;
        }

        // Load the XLS file
        FileInputStream fis = new FileInputStream(fileName);
        List<MpdItem> items = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(fis);

        HSSFSheet systemSheet = workbook.getSheet(sheetNames[0]);
        HSSFSheet structuralSheet = workbook.getSheet(sheetNames[1]);
        HSSFSheet zonalSheet = workbook.getSheet(sheetNames[2]);

        // system sheet load
        for (int i = 0; i < systemSheet.getLastRowNum(); i++) {
            HSSFRow row = systemSheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                HSSFCell uCell = row.getCell(0);
                HSSFCell countCell = row.getCell(1);
                if (uCell.getStringCellValue().equals("u") && countCell.getNumericCellValue() > 0.0) {
                    MpdItem item = processSystemSheet(row);
                    item.setEdition(edition);
                    item.setType("system");
                    items.add(item);
                }
            }
        }

        // structuralSheet load
        for (int i = 0; i < structuralSheet.getLastRowNum(); i++) {
            HSSFRow row = structuralSheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                HSSFCell uCell = row.getCell(0);
                HSSFCell countCell = row.getCell(1);
                if (uCell.getStringCellValue().equals("u") && countCell.getNumericCellValue() > 0.0) {
                    MpdItem item = processStructuralSheet(row);
                    item.setEdition(edition);
                    item.setType("structural");
                    items.add(item);
                }
            }
        }

        // zonalSheet load
        for (int i = 0; i < zonalSheet.getLastRowNum(); i++) {
            HSSFRow row = systemSheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                HSSFCell uCell = row.getCell(0);
                HSSFCell countCell = row.getCell(1);
                if (uCell.getStringCellValue().equals("u") && countCell.getNumericCellValue() > 0.0) {
                    MpdItem item = processZonalSheet(row);
                    item.setEdition(edition);
                    item.setType("zonal");
                    items.add(item);
                }
            }
        }
        workbook.close();
    }

    public static void importMpdTaskcards(DataImportService service, String fileName, String sheetName,
            MpdEdition edition)
            throws FileNotFoundException, IOException {
        if (fileName.isBlank()) {
            return;
        }

        // Load the XLS file
        FileInputStream fis = new FileInputStream(fileName);
        List<MpdTaskcard> items = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(fis);

        HSSFSheet taskcardsSheet = workbook.getSheet(sheetName);

        // taskcards load
        for (int i = 0; i < taskcardsSheet.getLastRowNum(); i++) {
            HSSFRow row = taskcardsSheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                HSSFCell uCell = row.getCell(0);
                HSSFCell countCell = row.getCell(1);
                if (uCell.getStringCellValue().equals("u") && countCell.getNumericCellValue() > 0.0) {
                    MpdTaskcard item = processTaskcardsSheet(row, edition, service);
                    items.add(item);
                }
            }
        }
        workbook.close();
    }

    private static MpdItem processSystemSheet(HSSFRow row) {
        MpdItem item = new MpdItem();
        HSSFCell numberCell = row.getCell(2);
        HSSFCell ammCell = row.getCell(3);
        HSSFCell catCell = row.getCell(4);
        HSSFCell taskCell = row.getCell(5);
        HSSFCell threshCell = row.getCell(6);
        HSSFCell repeatCell = row.getCell(7);
        HSSFCell zoneCell = row.getCell(8);
        HSSFCell accessCell = row.getCell(9);
        HSSFCell aplCell = row.getCell(10);
        HSSFCell engCell = row.getCell(11);
        HSSFCell mhCell = row.getCell(12);
        HSSFCell descriptionCell = row.getCell(13);

        item.setNumber(numberCell.getStringCellValue());
        item.setAmmReference(ammCell.getStringCellValue());
        item.setCat(catCell.getStringCellValue());
        item.setTask(taskCell.getStringCellValue());
        item.setThreshold(threshCell.getStringCellValue());
        item.setRepeat(repeatCell.getStringCellValue());
        item.setZone(zoneCell.getStringCellValue());
        item.setAccess(accessCell.getStringCellValue());
        item.setApl(aplCell.getStringCellValue());
        item.setEngine(engCell.getStringCellValue());
        item.setMh(mhCell.getStringCellValue());
        item.setDescription(descriptionCell.getStringCellValue());
        return item;
    }

    private static MpdItem processStructuralSheet(HSSFRow row) {
        MpdItem item = new MpdItem();
        HSSFCell numberCell = row.getCell(2);
        HSSFCell ammCell = row.getCell(3);
        HSSFCell pgmCell = row.getCell(4);
        HSSFCell zoneCell = row.getCell(5);
        HSSFCell accessCell = row.getCell(6);
        HSSFCell threshCell = row.getCell(7);
        HSSFCell repeatCell = row.getCell(8);
        HSSFCell aplCell = row.getCell(9);
        HSSFCell engCell = row.getCell(10);
        HSSFCell mhCell = row.getCell(11);
        HSSFCell descriptionCell = row.getCell(12);

        item.setNumber(numberCell.getStringCellValue());
        item.setAmmReference(ammCell.getStringCellValue());
        item.setPgm(pgmCell.getStringCellValue());
        item.setZone(zoneCell.getStringCellValue());
        item.setAccess(accessCell.getStringCellValue());
        item.setThreshold(threshCell.getStringCellValue());
        item.setRepeat(repeatCell.getStringCellValue());
        item.setApl(aplCell.getStringCellValue());
        item.setEngine(engCell.getStringCellValue());
        item.setMh(mhCell.getStringCellValue());
        item.setDescription(descriptionCell.getStringCellValue());
        return item;
    }

    private static MpdItem processZonalSheet(HSSFRow row) {
        MpdItem item = new MpdItem();
        HSSFCell numberCell = row.getCell(2);
        HSSFCell ammCell = row.getCell(3);
        HSSFCell zoneCell = row.getCell(4);
        HSSFCell accessCell = row.getCell(5);
        HSSFCell threshCell = row.getCell(6);
        HSSFCell repeatCell = row.getCell(7);
        HSSFCell aplCell = row.getCell(8);
        HSSFCell engCell = row.getCell(9);
        HSSFCell mhCell = row.getCell(10);
        HSSFCell descriptionCell = row.getCell(11);

        item.setNumber(numberCell.getStringCellValue());
        item.setAmmReference(ammCell.getStringCellValue());
        item.setZone(zoneCell.getStringCellValue());
        item.setAccess(accessCell.getStringCellValue());
        item.setThreshold(threshCell.getStringCellValue());
        item.setRepeat(repeatCell.getStringCellValue());
        item.setApl(aplCell.getStringCellValue());
        item.setEngine(engCell.getStringCellValue());
        item.setMh(mhCell.getStringCellValue());
        item.setDescription(descriptionCell.getStringCellValue());
        return item;
    }

    private static MpdTaskcard processTaskcardsSheet(HSSFRow row, MpdEdition edition, DataImportService service) {
        Map<String, MpdItem> map = service.getAllMpdItems(edition);

        MpdTaskcard card = new MpdTaskcard();
        HSSFCell numberCell = row.getCell(2);
        HSSFCell mpdCell = row.getCell(3);
        HSSFCell mrbCell = row.getCell(4);
        HSSFCell relatedCell = row.getCell(5);
        HSSFCell taskCell = row.getCell(6);
        HSSFCell titleCell = row.getCell(7);

        card.setNumber(numberCell.getStringCellValue());
        card.setMpdItem(map.get(mpdCell.getStringCellValue()));
        card.setMrbItem(mrbCell.getStringCellValue());
        card.setRelatedTasksString(relatedCell.getStringCellValue());
        card.setTask(taskCell.getStringCellValue());
        card.setTitle(titleCell.getStringCellValue());
        card.setEdition(edition);
        return card;
    }

    public static void importBoeingMhs(DataImportService service, String fileName, MpdEdition edition)
            throws IOException, FileNotFoundException, CsvValidationException {
        if (fileName.isBlank()) {
            return;
        }
        Set<String[]> set = new HashSet<>();
        List<MpdMh> mhs = new ArrayList<>(0);
        try (Reader reader = Files.newBufferedReader(Path.of(fileName))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    set.add(line);
                }
            }
            set.stream().forEach(strings -> {
                MpdMh mh = new MpdMh();
                mh.setMpdItemString(strings[0]);
                mh.setOpenMh(strings[1]);
                mh.setCloseMh(strings[2]);
                mh.setTotalMh(strings[3]);
                mh.setAccessString(strings[4]);
                mhs.add(mh);
            });
            service.saveAllMhs(mhs);
        }
    }
}
