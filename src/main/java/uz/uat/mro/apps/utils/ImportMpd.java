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
import org.apache.poi.ss.usermodel.CellType;

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
            HSSFCell uCell = row.getCell(0);
            if (uCell != null) {
                MpdItem item = processSystemSheet(row);
                item.setEdition(edition);
                item.setType("system");
                items.add(item);
            }
        }
        service.saveAllMpdItems(items);
        items.clear();

        // structuralSheet load
        for (int i = 0; i < structuralSheet.getLastRowNum(); i++) {
            HSSFRow row = structuralSheet.getRow(i);
            HSSFCell uCell = row.getCell(0);
            if (uCell != null) {
                MpdItem item = processStructuralSheet(row);
                item.setEdition(edition);
                item.setType("structural");
                items.add(item);
            }
        }
        service.saveAllMpdItems(items);
        items.clear();

        // zonalSheet load
        for (int i = 0; i < zonalSheet.getLastRowNum(); i++) {
            HSSFRow row = zonalSheet.getRow(i);
            HSSFCell uCell = row.getCell(0);
            if (uCell != null) {
                MpdItem item = processZonalSheet(row);
                item.setEdition(edition);
                item.setType("zonal");
                items.add(item);
            }
        }
        service.saveAllMpdItems(items);
        items.clear();
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
        List<MpdTaskcard> cards = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(fis);

        HSSFSheet taskcardsSheet = workbook.getSheet(sheetName);

        // taskcards load
        for (int i = 0; i < taskcardsSheet.getLastRowNum(); i++) {
            HSSFRow row = taskcardsSheet.getRow(i);
            HSSFCell uCell = row.getCell(0);
            if (uCell != null) {
                MpdTaskcard card = processTaskcardsSheet(row, edition, service);
                cards.add(card);
            }
        }
        service.saveAllTaskcards(cards);
        workbook.close();
    }

    private static MpdItem processSystemSheet(HSSFRow row) {
        MpdItem item = new MpdItem();
        String[] array = new String[12];
        for (int i = 2; i < row.getLastCellNum(); i++) {
            int arrayIndex = i - 2;
            HSSFCell cell = row.getCell(i);
            CellType cellType = cell.getCellType();

            switch (cellType) {
                case BLANK, STRING: {
                    array[arrayIndex] = cell.getStringCellValue();
                    break;
                }
                case NUMERIC: {
                    array[arrayIndex] = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                default:
                    break;
            }
        }
        item.setNumber(array[0]);
        item.setAmmReference(array[1]);
        item.setCat(array[2]);
        item.setTask(array[3]);
        item.setThreshold(array[4]);
        item.setRepeat(array[5]);
        item.setZone(array[6]);
        item.setAccess(array[7]);
        item.setApl(array[8]);
        item.setEngine(array[9]);
        item.setMh(array[10]);
        item.setDescription(array[11]);
        return item;
    }

    private static MpdItem processStructuralSheet(HSSFRow row) {
        MpdItem item = new MpdItem();
        String[] array = new String[11];
        for (int i = 2; i < row.getLastCellNum(); i++) {
            int arrayIndex = i - 2;
            HSSFCell cell = row.getCell(i);
            array[arrayIndex] = parseCell(cell);
        }
        item.setNumber(array[0]);
        item.setAmmReference(array[1]);
        item.setPgm(array[2]);
        item.setZone(array[3]);
        item.setAccess(array[4]);
        item.setThreshold(array[5]);
        item.setRepeat(array[6]);
        item.setApl(array[7]);
        item.setEngine(array[8]);
        item.setMh(array[9]);
        item.setDescription(array[10]);
        return item;
    }

    private static MpdItem processZonalSheet(HSSFRow row) {

        MpdItem item = new MpdItem();
        String[] array = new String[10];

        for (int i = 2; i < row.getLastCellNum(); i++) {
            int arrayIndex = i - 2;
            HSSFCell cell = row.getCell(i);
            array[arrayIndex] = parseCell(cell);
        }
        item.setNumber(array[0]);
        item.setAmmReference(array[1]);
        item.setZone(array[2]);
        item.setAccess(array[3]);
        item.setThreshold(array[4]);
        item.setRepeat(array[5]);
        item.setApl(array[6]);
        item.setEngine(array[7]);
        item.setMh(array[8]);
        item.setDescription(array[9]);
        return item;
    }

    private static MpdTaskcard processTaskcardsSheet(HSSFRow row, MpdEdition edition, DataImportService service) {
        MpdTaskcard card = new MpdTaskcard();
        String[] array = new String[7];

        for (int i = 2; i < row.getLastCellNum(); i++) {
            int arrayIndex = i - 2;
            HSSFCell cell = row.getCell(i);
            array[arrayIndex] = parseCell(cell);
        }
        card.setNumber(array[0]);
        card.setMpdItem(service.findByNumberAndEdition(array[1], edition));
        card.setMrbItem(array[2]);
        card.setRelatedTasksString(array[3]);
        card.setTask(array[4]);
        card.setTitle(array[5]);
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

    private static String parseCell(HSSFCell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case BLANK, STRING: {
                return cell.getStringCellValue().replaceAll("\n", " ");
            }
            case NUMERIC: {
                return String.valueOf(cell.getNumericCellValue());
            }
            default:
                return null;
        }
    }

}
