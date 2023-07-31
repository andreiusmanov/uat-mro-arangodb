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
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.vaadin.flow.component.notification.Notification;

import uz.uat.mro.apps.model.alt.aircraft.AircraftAccess;
import uz.uat.mro.apps.model.alt.aircraft.AircraftSubzone;
import uz.uat.mro.apps.model.alt.aircraft.AircraftZone;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;
import uz.uat.mro.apps.model.alt.library.MpdEdition;
import uz.uat.mro.apps.model.alt.library.MpdItem;
import uz.uat.mro.apps.model.alt.library.MpdMh;
import uz.uat.mro.apps.model.alt.library.MpdTaskcard;
import uz.uat.mro.apps.model.library.service.DataImportService;

public class ImportMpd {

    public static void importBoeingZones(DataImportService service, String fileName, MajorModel model)
            throws IOException, CsvValidationException {
        if (fileName.isBlank()) {
            Notification.show("Файл не найден. Проверьте путь к файлу.");
            return;
        }
        Set<String[]> set = new HashSet<>();
        Set<AircraftZone> zones = new HashSet<>(0);
        try (Reader reader = Files.newBufferedReader(Path.of(fileName))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    set.add(line);
                }
            }
            set.stream().forEach(strings -> {
                AircraftZone zone = new AircraftZone();
                zone.setModel(model);
                zone.setCode(strings[0]);
                zone.setName(strings[1]);
                zone.setModel(model);
                zones.add(zone);
            });
            service.saveAllZones(zones);
        }
    }

    public static void importBoeingSubzones(DataImportService service, String filePath, MajorModel model)
            throws IOException, CsvException {

        if (filePath.isBlank()) {
            return;
        }
        List<String[]> list = new ArrayList<>();
        Set<AircraftSubzone> subzones = new HashSet<>(0);
        Map<String, AircraftZone> zonesMap = service.getAllZones(model);
        try (Reader reader = Files.newBufferedReader(Path.of(filePath))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                list = csvReader.readAll();
            }

            List<String> codes = new ArrayList<>(0);
            list.stream().forEach(array -> {
                AircraftSubzone subzone = new AircraftSubzone();
                subzone.setModel(model);
                if (!codes.contains(array[0])) {
                    codes.add(array[0]);
                    subzone.setCode(array[0]);
                    subzone.setName(array[1]);
                    char s = subzone.getCode().charAt(0);
                    String res = s + "00";
                    subzone.setZone(zonesMap.getOrDefault(res, null));
                    subzones.add(subzone);
                }
            });
            service.saveAllSubzones(subzones);
        }
    }

    public static void importBoeingAccesses(DataImportService service, String accessesFile, MajorModel model)
            throws IOException, CsvValidationException {
        if (accessesFile.isBlank()) {
            Notification.show("Файл не найден. Проверьте путь к файлу.");
            return;
        }
        Set<AircraftAccess> accesses = new HashSet<>(0);
        Map<String, AircraftSubzone> subzonesMap = service.getAllSubzones(model);
        List<String[]> accessesArray = normalizeTable(accessesFile);

        List<String> d = new ArrayList<>();

        accessesArray.stream().forEach(array -> {
            if (!d.contains(array[0])) {
                d.add(array[0]);
                AircraftAccess access = new AircraftAccess();
                access.setModel(model);
                access.setSynthetic(false);
                access.setSubzone(subzonesMap.get(array[5]));
                access.setNumber(array[0]);
                access.setOpen(new BigDecimal(array[1].isBlank() ? "0.0" : array[1]));
                access.setClose(new BigDecimal(array[2].isBlank() ? "0.0" : array[2]));
                access.setAplEngine(array[3]);
                access.setName(array[4]);
                access.setSubzoneString(array[5]);
                accesses.add(access);
            }
        });
        service.saveAllAccesses(accesses);
    }

    public static void importBoeingAccessesSynth(DataImportService service, String syntheticAccessesFile,
            MajorModel model) throws IOException, CsvValidationException {
        if (syntheticAccessesFile.isBlank()) {
            Notification.show("Файл не найден. Проверьте путь к файлу.");
            return;
        }
        Set<AircraftAccess> accesses = new HashSet<>(0);
        Map<String, AircraftSubzone> subzonesMap = service.getAllSubzones(model);
        List<String[]> synthAccessesArray = normalizeTable(syntheticAccessesFile);
        List<String> d = new ArrayList<>();
        synthAccessesArray.stream().forEach(array -> {
            if (!d.contains(array[0])) {
                d.add(array[0]);
                AircraftAccess access2 = new AircraftAccess();
                access2.setModel(model);
                access2.setSynthetic(true);
                access2.setSubzone(subzonesMap.get(array[0].substring(0, 3)));
                access2.setNumber(String.valueOf(array[0]));
                access2.setOpen(new BigDecimal(array[1].isBlank() ? "0.0" : array[1]));
                access2.setClose(new BigDecimal(array[2].isBlank() ? "0.0" : array[2]));
                access2.setName(array[3]);
                access2.setMmReference(array[4]);
                access2.setSubzoneString(array[0].substring(0, 3));
                accesses.add(access2);
            }
        });
        service.saveAllAccesses(accesses);
    }

    private static List<String[]> normalizeTable(String fileName) throws IOException, CsvValidationException {
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
                reader.close();
                csvReader.close();
                return l;
            }

        }

    }

    public static void importMpdItems(DataImportService service, String fileName, String[] sheetNames,
            MpdEdition edition)
            throws FileNotFoundException, IOException {
        if (fileName.isBlank()) {
            Notification.show("Файл не найден. Проверьте путь к файлу.");
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
        for (int i = 0; i < systemSheet.getLastRowNum() + 1; i++) {
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
        for (int i = 0; i < structuralSheet.getLastRowNum() + 1; i++) {
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
        for (int i = 0; i < zonalSheet.getLastRowNum() + 1; i++) {
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
            Notification.show("Файл не найден. Проверьте путь к файлу.");
            return;
        }

        // Load the XLS file
        FileInputStream fis = new FileInputStream(fileName);
        List<MpdTaskcard> cards = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet taskcardsSheet = workbook.getSheet(sheetName);
        // taskcards load
        for (int i = 0; i < taskcardsSheet.getLastRowNum() + 1; i++) {
            HSSFRow row = taskcardsSheet.getRow(i);
            HSSFCell uCell = row.getCell(0);
            if (uCell != null) {
                MpdTaskcard card = processTaskcardsSheet(row, edition, service);
                cards.add(card);
            }
        }
        service.saveAllTaskcards(cards);
        workbook.close();
        service.setMpdItems2Taskcards(edition);
        service.setTaskcards2MpdItems(edition);
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
        item.setAmmReference(array[1].replace("\n", ", ").trim());
        item.setCat(array[2].replace(".0", "").trim());
        item.setTask(array[3]);
        item.setThreshold(array[4].replace("\n", ", ").trim());
        item.setRepeat(array[5].replace("\n", ", ").trim());
        item.setZone(array[6].replace("\n", ", ").replace(".0", "").trim());
        item.setAccess(array[7].replace("\n", ", ").replace(".0", "").trim());
        item.setApl(array[8].replace("\n", ", ").replace(".0", "").trim());
        item.setEngine(array[9].replace("\n", ", ").replace(".0", "").trim());
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
        item.setAmmReference(array[1].replace("\n", ", ").trim());
        item.setPgm(array[2].replace(".0", "").trim());
        item.setZone(array[3].replace("\n", ", ").replace(".0", "").trim());
        item.setAccess(array[4].replace("\n", ", ").replace(".0", "").trim());
        item.setThreshold(array[5].replace("\n", ", ").trim());
        item.setRepeat(array[6].replace("\n", ", ").trim());
        item.setApl(array[7].replace("\n", ", ").replace(".0", "").trim());
        item.setEngine(array[8].replace("\n", ", ").replace(".0", "").trim());
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
        item.setAmmReference(array[1].replace("\n", ", ").trim());
        item.setZone(array[2].replace("\n", ", ").replace(".0", "").trim());
        item.setAccess(array[3].replace("\n", ", ").replace(".0", "").trim());
        item.setThreshold(array[4].replace("\n", ", ").trim());
        item.setRepeat(array[5].replace("\n", ", ").trim());
        item.setApl(array[6].replace("\n", ", ").replace(".0", "").trim());
        item.setEngine(array[7].replace("\n", ", ").replace(".0", "").trim());
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
        card.setMpdItemString(array[1]);
        card.setMrbItem(array[2].replace("\n", ", ").trim());
        card.setRelatedTasksString(array[3].replace("\n", ", ").trim());
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
        List<String[]> set = normalizeTable(fileName);
        List<MpdMh> mhs = new ArrayList<>(0);

        set.stream().forEach(strings -> {
            MpdMh mh = new MpdMh();
            mh.setMpdItemString(strings[0]);
            mh.setAccessString(strings[4].trim().replaceAll(" ", ", "));
            mh.setEdition(edition);

            mh.setAccessMh(strings[1]);
            mh.setTaskcardMh(strings[2]);
            mh.setTotalMh(strings[3]);
            mhs.add(mh);
        });
        service.saveAllMhs(mhs);
        service.setMpdItems2Mhs(edition);
        service.setMhs2MpdItems(edition);
    }

    private static String parseCell(HSSFCell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case BLANK, STRING: {
                return cell.getStringCellValue().replaceAll("\n", ", ");
            }
            case NUMERIC: {
                return String.valueOf(cell.getNumericCellValue());
            }
            default:
                return null;
        }
    }

}
