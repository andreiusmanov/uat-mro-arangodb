package uz.uat.mro.apps.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import uz.uat.mro.apps.model.activity.entity.MaintenanceCard;
import uz.uat.mro.apps.model.activity.entity.Project;
import uz.uat.mro.apps.model.activity.entity.TaskGroup;
import uz.uat.mro.apps.model.activity.service.MaintenanceCardsService;
import uz.uat.mro.apps.model.library.entity.MpdEdition;

public class ImportMC {

    /**
     * import data from xls
     * 
     * @param fileName
     * @param project
     * @throws CsvException
     * @throws IOException
     */
    public static void importMaintenanceCards(MaintenanceCardsService service, String fileName, Project project)
            throws IOException, CsvException {
        if (fileName.isBlank()) {
            return;
        }

        MpdEdition edition = project.getEdition();

        List<String[]> cards = normalizeCards(fileName);
        List<MaintenanceCard> list = new ArrayList<>();

        List<TaskGroup> taskGroups = service.findAllTaskgroups();

        cards.stream().forEach(card -> {
            MaintenanceCard m = new MaintenanceCard();
            m.setTaskGroup((TaskGroup) findObjectByProperty(taskGroups, "name", card[0]));
            m.setSequence(card[1]);
            m.setNumber(card[2]);
            m.setDescription(card[3]);
            m.setRemarks(card[4]);
            m.setValid(true);
            System.out.println(m.getNumber());
            if (m.getTaskGroup().getId().equals("ht") || m.getTaskGroup().getId().equals("routine")) {
                m.setTaskcard(service.findTaskcardByNumberAndEdition(card[2], edition));
            }
            m.setProject(project);
            list.add(m);
        });

        service.saveAll(list);

    }

    private static List<String[]> normalizeCards(String fileName) throws IOException, CsvException {
        try (Reader reader = Files.newBufferedReader(Path.of(fileName))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    private static <T> T findObjectByProperty(List<T> list, String propertyName, Object propertyValue) {
        for (T object : list) {
            try {
                Field field = object.getClass().getDeclaredField(propertyName);
                field.setAccessible(true);
                Object value = field.get(object);
                if (value.equals(propertyValue)) {
                    return object;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void importCards(MaintenanceCardsService service, String directoryName, Project project) throws IOException {
        if (directoryName.isBlank()) {
            return;
        }
        File dirName = new File(directoryName);
        if (dirName.isDirectory()) {
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".pdf");
                }
            };
            String[] file = dirName.list(filter);

            PDDocument document = PDDocument.load(new File("file_path.pdf"));

        }

    }

}
